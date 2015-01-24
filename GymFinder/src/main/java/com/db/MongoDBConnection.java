package com.db;

import java.net.UnknownHostException;
import com.mongodb.MongoException;
import com.mongodb.DB;
import com.mongodb.MongoClient;
 
public class MongoDBConnection {
  
	private MongoClient mongo = null;
	private DB db = null;
	
	public MongoDBConnection() {
		try {	
			mongo = new MongoClient("localhost", 27017);
			db = mongo.getDB("gymsDB");			
		}
	 
		catch (UnknownHostException e) {
			e.printStackTrace();
		 } catch (MongoException e) {
			e.printStackTrace();
		 }
	}
	
	public void CloseConnection() {
		mongo.close();
	}

	public  DB getDb() {
		return this.db;
	}

 
}