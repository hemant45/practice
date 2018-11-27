package com.example.practice.Service;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestConnection {

	static String formjson = "{\"headers\":{\"Authorization\":\"Token ${AUTH_TOKEN}\",\"Prefer\":\"respond-sync,wait=1500\",\"Content-Type\":\"application/json\"},\"method\":\"POST\",\"auth\":{\"headers\":{\"Prefer\":\"respond-async\",\"Content-Type\":\"application/json\"},\"tokenPath\":\"/value\",\"type\":\"BASIC\",\"body\":\"{\\\"Credentials\\\":{\\\"Username\\\":\\\"9010843\\\",\\\"Password\\\":\\\"5kMEz7Tg\\\"}}\",\"url\":\"https://hosted.datascopeapi.reuters.com/RestApi/v1/Authentication/RequestToken\"},\"name\":\"TR Connector\",\"id\":\"1441\",\"body\":\"{\\\"ExtractionRequest\\\":{\\\"@odata.type\\\":\\\"#ThomsonReuters.Dss.Api.Extractions.ExtractionRequests.EndOfDayPricingExtractionRequest\\\",\\\"ContentFieldNames\\\":[\\\"Instrument ID\\\",\\\"RIC Root\\\",\\\"Trade Date\\\",\\\"Asset SubType Description\\\",\\\"Exchange Code\\\",\\\"Expiration Date\\\",\\\"Contract Month and Year\\\",\\\"Contributor Code Description\\\",\\\"Currency Code\\\",\\\"Lot Units\\\",\\\"Settlement Price\\\",\\\"Open Price\\\",\\\"Close Price\\\",\\\"Ask Price\\\",\\\"Bid Price\\\",\\\"High Price\\\",\\\"Low Price\\\"],\\\"IdentifierList\\\":{\\\"@odata.type\\\":\\\"#ThomsonReuters.Dss.Api.Extractions.ExtractionRequests.InstrumentIdentifierList\\\",\\\"InstrumentIdentifiers\\\":[{\\\"Identifier\\\":\\\"1BOF9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"},{\\\"Identifier\\\":\\\"1BOG9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"},{\\\"Identifier\\\":\\\"1BOH9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"},{\\\"Identifier\\\":\\\"1BOJ9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"},{\\\"Identifier\\\":\\\"1BOK9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"},{\\\"Identifier\\\":\\\"1BOM9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"},{\\\"Identifier\\\":\\\"1BON9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"},{\\\"Identifier\\\":\\\"1BOQ9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"},{\\\"Identifier\\\":\\\"1BOU9\\\",\\\"IdentifierType\\\":\\\"Ric\\\"}],\\\"ValidationOptions\\\":{\\\"AllowOpenAccessInstruments\\\":true},\\\"UseUserPreferencesForValidationOptions\\\":false},\\\"Condition\\\":{\\\"ScalableCurrency\\\":true}}}\",\"url\":\"https://hosted.datascopeapi.reuters.com/RestApi/v1/Extractions/Extract\"}";
	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub
		JSONObject form = new JSONObject(formjson);
		JSONObject auth = form.getJSONObject("auth");
		HttpClient client = HttpClientBuilder.create().build();
		
		
		if (auth.get("type") == "NOAUTH") {
			
		}
		System.out.println(auth.get("type"));

	}

}
