package com.example.fix;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONArray job = new JSONArray("[{\"id\":\"87\",\"name\":\"TradeDate\",\"type\":\"DATE\"},{\"id\":\"33\",\"name\":\"NoSides\",\"type\":\"ARRAY\",\"elements\":[[{\"type\":\"ARRAY\",\"elements\":[{\"id\":\"98\",\"name\":\"Side\",\"type\":\"NUMBER\"},{\"id\":\"55\",\"name\":\"key1\",\"type\":\"STRING\"}]}]]}]");

		for(int i =0; i< job.length(); i ++) {
			System.out.println(job.getJSONObject(i).get("id"));
			
		}
	
	}
}
