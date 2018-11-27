package com.example.practice;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import com.example.exceptions.FunctionalException;

public class TestConnectionRest {

	public static void main(String[] args) throws FunctionalException {
		
		System.out.println("testConnection response: " + testConnection());

	}
	
	private static String testConnection() throws FunctionalException{
		try {
			String userName = "9010843";
			String password = "5kMEz7Tg";
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(
					"https://hosted.datascopeapi.reuters.com/RestApi/v1/Authentication/RequestToken");
			post.setHeader("Prefer", "respond-async");
			post.setHeader("Content-Type", "application/json");

			JSONObject inputBody = new JSONObject();
			JSONObject auth = new JSONObject();
			auth.put("Username", userName);
			auth.put("Password", password);
			inputBody.put("Credentials", auth);

			StringEntity input = new StringEntity(inputBody.toString());
			input.setContentType("application/json");
			post.setEntity(input);

			HttpResponse response = client.execute(post);
			
			System.out.println("Response: "+ response.getEntity());
			System.out.println(response.getStatusLine().getStatusCode());
			int responseStatus = response.getStatusLine().getStatusCode();
			
						
			return ( HTTPResponseStatusMessage.getMessage(responseStatus).getMessage());
			
						
		}  catch (Exception e) {
			return ("Exception: " + e.getMessage());
		}
	}

}
