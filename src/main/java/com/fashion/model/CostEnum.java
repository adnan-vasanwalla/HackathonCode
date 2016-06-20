package com.fashion.model;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public enum CostEnum {

	LESS_THAN_1000("Less than 1000", BasicDBObjectBuilder.start("$lt", 1000).append("$gt", 0).get()),
	THOUSAND_TO_TWO_THOUSAND("1000-2000", BasicDBObjectBuilder.start("$lt", 2000).append("$gt", 1000).get()),
	TWO_THOUSAND_TO_THREE_THOUSAND("2000-3000", BasicDBObjectBuilder.start("$lt", 3000).append("$gt", 2000).get()),
	THREE_THOUSAND_TO_FOUR_THOUSAND("3000-4000", BasicDBObjectBuilder.start("$lt", 4000).append("$gt", 3000).get()),
	FOUR_THOUSAND_TO_FIVE_THOUSAND("4000-5000", BasicDBObjectBuilder.start("$lt", 5000).append("$gt", 4000).get()),
	GREATER_THAN_5000("Greater than 5000", BasicDBObjectBuilder.start("$gt", 5000).append("$gt", 0).get());
	
	private String name;
	private DBObject object;
	
	private CostEnum(String name, DBObject object) {
		this.name= name;
		this.object = object;
	}
	
	public static DBObject getDBObjectFromString(String name) {
		CostEnum[] values = CostEnum.values();
		for(CostEnum value : values) {
			if(value.name.equals(name)) {
				return value.object;
			}
		}
		return null;
	}
	
}
