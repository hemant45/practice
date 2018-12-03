package com.example.practice.Dao;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Repository
public class AppDAO {

	public void getAppDetails() throws InterruptedException{
		 MongoClient mclient = new MongoClient("localhost",27017);
		DB database = mclient.getDB("localDB");
	
		DBCollection allocationMetaCollection = 
				database.getCollection("AllocationMeta");
		
		System.out.println(allocationMetaCollection.getCount());
		
		Cursor iterable = allocationMetaCollection.find();
				
		while(iterable.hasNext()) {
			System.out.println(iterable.next());
			JSONObject dataRetreived = (JSONObject)iterable.next(); 
			System.out.println(dataRetreived.toString());
			Thread.currentThread().sleep(20);
		}
				
		
	}
}
