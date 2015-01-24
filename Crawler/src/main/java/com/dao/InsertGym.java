package com.dao;

import com.db.MongoDBConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class InsertGym {
	
	private MongoDBConnection db;

	public InsertGym() {
		db = new MongoDBConnection();
	}

	protected void finalize() throws Throwable {
		db.CloseConnection();
	}

	public void insertGymDetails(String name, String contact, String address, String website, String hours) {
		if(db == null || db.getDb() == null )
			return;
		try {
			DBCollection gyms = db.getDb().getCollection("gymnasium");
			BasicDBObject gym = new BasicDBObject();
			gym.put("_id", name + contact);
	        gym.put("name", name);
	        gym.put("contact", contact);
	        gym.put("address", address);
	        gym.put("website", website);
	        gym.put("hours_of_operation", hours);
	        gyms.insert(gym);	
		}
		catch(Exception e){
		}
	}
}
