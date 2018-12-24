package com.example.fix;



import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Spliterator;
import java.util.Spliterators;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import biz.onixs.fix.dictionary.Version;
import biz.onixs.fix.engine.Engine;
import biz.onixs.fix.engine.Session;
import biz.onixs.fix.parser.Field;
import biz.onixs.fix.parser.Group;
import biz.onixs.fix.parser.Message;

public class FixMessageParser {

	//private static String jsonMetaString = "{\"type\":\"OBJECT\",\"elements\":[{\"id\":\"75\",\"name\":\"TradeDate\",\"type\":\"DATE\"},{\"id\":\"33\",\"name\":\"NoSides\",\"type\":\"ARRAY\",\"elements\":[{\"id\":\"98\",\"name\":\"Side\",\"type\":\"NUMBER\"},{\"id\":\"55\",\"name\":\"key1\",\"type\":\"STRING\"}]}]}";

	private static String jsonMetaString = "{\"type\":\"OBJECT\",\"elements\":[{\"id\":\"8\",\"name\":\"Version\",\"type\":\"STRING\"},{\"id\":\"9\",\"name\":\"Price\",\"type\":\"INTEGER\"},{\"id\":\"35\",\"name\":\"CODE\",\"type\":\"STRING\"},{\"id\":\"60\",\"name\":\"TRADE DATE\",\"type\":\"DATE\"},{\"id\":\"386\",\"name\":\"NoTradingSessions\",\"type\":\"ARRAY\",\"elements\":[{\"id\":\"336\",\"name\":\"TradingSessionID\",\"type\":\"NUMBER\"},{\"id\":\"625\",\"name\":\"TradingSessionSubID\",\"type\":\"NUMBER\"}]}]}";

	Message message= new Message("");

	//static JSONObject parsedJSON = new JSONObject();

	public static void main(String[] args) {

		JSONObject jsonMeta = new JSONObject(jsonMetaString);
		Map<String, String> fieldMap = new HashMap<String, String>();

		Engine engine = null;
		Session session = null;
		try {
			Properties properties = new Properties();

			properties.put("SocketConnectHost", "192.214.97.195");
			properties.put("SocketConnectPort", "41811");
			properties.put("ConnectionRetries.Interval", 1000);
			properties.put("LicenseFile", "D:/softwares/jars/OnixS.lic");


			properties.put("SenderCompID", "EKAPLUS");
			properties.put("TargetCompID", "REDITKTS");

			properties.put("OnbehalfOfCompID", "REDI");
			properties.put("OnbehalfOfSubID", "woner1");

			engine = Engine.init(properties);

			Message msg1 = Message.create("A", Version.FIX44);
			msg1.init("8=FIX.4.29=13235=D34=1649=BANZAI52=20160312-20:00:05.51856=EXEC11=145781280539421=138=10040=154=155=VOD.L59=060=20160312-20:00:05.51810=198386=3336=12323336=564625=12");

			System.out.println("Message : " + msg1.toString());

			System.out.println("parsed : " + parse(jsonMeta, msg1));

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(engine != null) {
				engine.shutdown();
			}
		}



	}


	public static JSONObject parse(JSONObject jsonMeta, Message msg) {
		
		JSONObject parsedJSON = new JSONObject();
		
		if(jsonMeta.get("type")!= null && jsonMeta.get("type").equals("OBJECT")) {
			JSONArray childArray = (JSONArray) jsonMeta.get("elements");

			for(int i=0; i< childArray.length(); i++) {
				parsedJSON= mergeJSONObjects(parsedJSON, parse(childArray.getJSONObject(i),msg));
			}
		} 
		
		
		else if (jsonMeta.get("type").equals("ARRAY")) {
			parsedJSON.put((String) jsonMeta.get("name"),
					msg.get(jsonMeta.getInt("id")));
			Group repeatingGroup = msg.getGroup(jsonMeta.getInt("id"));
			if(repeatingGroup != null) {
				JSONArray sidesJson = new JSONArray();			
			}
			parsedJSON.put((String) jsonMeta.get("name"),
					msg.get(jsonMeta.getInt("id")));

		} 		
		else {
			parsedJSON.put((String) jsonMeta.get("name"),
					msg.get(jsonMeta.getInt("id")));

		} 
		return parsedJSON;
	}

	public static JSONArray parseRepeatingGroups(JSONObject jsonMeta, Message msg) {
		
		JSONArray parsedJSON = new JSONArray();
		
		if(jsonMeta.get("type")!= null && jsonMeta.get("type").equals("OBJECT")) {
			JSONArray childArray = (JSONArray) jsonMeta.get("elements");

			for(int i=0; i< childArray.length(); i++) {
				parsedJSON.put(parse(childArray.getJSONObject(i),msg));
			}
		} 
		
		
		else if (jsonMeta.get("type").equals("ARRAY")) {
			Group repeatingGroup = msg.getGroup(jsonMeta.getInt("id"));
			if(repeatingGroup != null) {
				JSONArray sidesJson = new JSONArray();
			}

		} 		
		else {
			parsedJSON.put(new JSONObject());

		} 
		return parsedJSON;
	}


	public static JSONObject mergeJSONObjects(JSONObject json1, JSONObject json2) {
		JSONObject mergedJSON = new JSONObject();
		try {
			if(json1 == null || json1.length()==0) {
				return json2;
			} else if (json2 == null || json2.length()==0) {
				return json1;
			} else {
				mergedJSON = new JSONObject(json1, JSONObject.getNames(json1));
				for (String crunchifyKey : JSONObject.getNames(json2)) {
					mergedJSON.put(crunchifyKey, json2.get(crunchifyKey));
				}	
			}
			
 
		} catch (JSONException e) {
			throw new RuntimeException("JSON Exception" + e);
		}
		return mergedJSON;
	}

	/*Using Message
	 */
	/*public static JSONObject parse(JSONObject jsonMeta, Message msg) {
		JSONObject parsedJSON = new JSONObject();

		if(jsonMeta.get("type")!= null && jsonMeta.get("type").equals("OBJECT")) {
			JSONArray childArray = (JSONArray) jsonMeta.get("elements");

			for(int i=0; i< childArray.length(); i++) {
				parse(childArray.getJSONObject(i),msg);
			}
		} else if (jsonMeta.get("type").equals("ARRAY")) {

		} else {
			parsedJSON.append((String) jsonMeta.get("name"),
					msg.get(jsonMeta.getInt("id")));
		} 
		return parsedJSON;
	}*/

	public static JSONObject parse(JSONObject jsonMeta, String[] msgTokens) {
		JSONObject parsedJson = new JSONObject();

		if(jsonMeta.get("type").equals("OBJECT")) {
			JSONArray childArray = (JSONArray) jsonMeta.get("elements");

			for(int i=0; i< childArray.length(); i++) {

				for (String str : msgTokens) {
					if(childArray.getJSONObject(i).get("id").equals(str.substring(0,str.indexOf('=')))) {
						parsedJson.append((String) childArray.getJSONObject(i).get("name"), 
								parse(childArray.getJSONObject(i),msgTokens));
					}
				}


			}
		} else if(jsonMeta.get("type").equals("ARRAY")) {


			JSONArray childArray = (JSONArray) jsonMeta.get("elements");
			for(int i=0; i< childArray.length(); i++) {

				for (String str : msgTokens) {
					if(childArray.getJSONObject(i).get("id").equals(str.substring(0,str.indexOf('=')))) {
						parsedJson.append((String) childArray.getJSONObject(i).get("name"), 
								parse(childArray.getJSONObject(i),msgTokens));
					}
				}


			}

		}
		else {
			for (String str : msgTokens) {
				if(jsonMeta.get("id").equals(str.substring(0,str.indexOf('=')))) {
					parsedJson.append((String) jsonMeta.get("name"),
							(String)str.substring(str.indexOf('=')+1, str.length()));
				}
			}
		}


		System.out.println(parsedJson.toString());
		return parsedJson;
	}


	/*	public static Map<String, String> id2FieldMap(JSONObject jsonMeta, boolean isChild){
		Map<String, String> fieldMap = new HashMap<String, String>();
		if(jsonMeta.get("type").equals("OBJECT")) {
			JSONArray childArray = (JSONArray) jsonMeta.get("elements");
			for(int i=0; i< childArray.length(); i++) {
				fieldMap.putAll(id2FieldMap(childArray.getJSONObject(i), false));
			}
		}	
		else if (jsonMeta.get("type").equals("ARRAY")) {
			fieldMap.put((String)jsonMeta.get("id"), (String)jsonMeta.get("name"));
			JSONArray childArray = (JSONArray) jsonMeta.get("elements");
			for(int i=0; i< childArray.length(); i++) {
				fieldMap.putAll(id2FieldMap(childArray.getJSONObject(i), true));
			}
		} else {
			fieldMap.put((String)jsonMeta.get("id"), (String)jsonMeta.get("name"));
		}
		System.out.println(fieldMap.toString());
		return fieldMap;

	}*/

}
