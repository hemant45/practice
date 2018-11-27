package com.example.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoTest {

	static MongoClient mc= new MongoClient("localhost",27017);
	static MongoDatabase db = mc.getDatabase("localDB");
	
	public static void main(String [] args) {
			
		//System.out.println(db.getCollection("CollectionList").count());
		 findCollectionDataByCollectionId("130642");
	}
	
	public static void  findCollectionDataByCollectionId(String id) {
		MongoCollection<Document> mongCol = db.getCollection("CollectionData");
		List<Document> lst =  new ArrayList<Document>();
		
		mongCol.find(new Document("collection_id", id)).forEach(new Consumer<Document>() {
			@Override
			public void accept(Document doc) {
				lst.add(doc);
			}
		});
		
		for(Document d : lst) {
			System.out.println(d.toString());
		}
	}
}
