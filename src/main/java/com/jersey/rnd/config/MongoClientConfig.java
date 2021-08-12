package com.jersey.rnd.config;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoClientConfig {
	
	private static MongoClient mongoClient;
	private static final String DATA_BASE = "vaccine";
	
	private MongoClientConfig() {}
	
	static {

		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                                                                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                                                                .codecRegistry(codecRegistry)
                                                                .build();
        mongoClient = MongoClients.create(clientSettings);
	}
	
	public static MongoDatabase getDatabase() {
		
		return mongoClient.getDatabase(DATA_BASE);
	}
	
}