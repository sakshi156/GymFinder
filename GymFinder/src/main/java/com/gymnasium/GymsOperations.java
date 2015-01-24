package com.gymnasium;

import java.util.ArrayList;
import java.util.List;

import com.db.MongoDBConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.regex.Pattern;

public class GymsOperations {
	
	private MongoDBConnection db;

	public GymsOperations() {
		db = new MongoDBConnection();
	}

	protected void finalize() throws Throwable {
		db.CloseConnection();
	}
	
	public List<Gymnasium> fetchGymsDetails(String location) {
		if(db == null || db.getDb() == null )
			return null;
		List<Gymnasium> gymsList = new ArrayList<Gymnasium>();
		DBCollection gyms = db.getDb().getCollection("gymnasium");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("address", Pattern.compile(location, Pattern.CASE_INSENSITIVE));
        DBCursor records = gyms.find(searchQuery);

        while(records.hasNext()){
            DBObject gymRecord = records.next();
            Gymnasium gym = new Gymnasium();
            
			gym.setName(String.valueOf(gymRecord.get("name")));
			gym.setContact(String.valueOf(gymRecord.get("contact")));
			gym.setAddress(String.valueOf(gymRecord.get("address")));
			gym.setWebsite(String.valueOf(gymRecord.get("website")));
			gym.setHoursOfOperation(String.valueOf(gymRecord.get("hours_of_operation")));
            
			gymsList.add(gym);
        }

		return gymsList;
	}

}
