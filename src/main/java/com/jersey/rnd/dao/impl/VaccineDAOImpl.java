package com.jersey.rnd.dao.impl;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.BsonObjectId;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.jvnet.hk2.annotations.Service;

import com.jersey.rnd.config.MongoClientConfig;
import com.jersey.rnd.dao.VaccineDAO;
import com.jersey.rnd.model.Vaccine;
import com.jersey.rnd.model.constant.VaccineConstant;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

@Service
public class VaccineDAOImpl implements VaccineDAO {

	@Override
	public Optional<Vaccine> findById(String vaccineId) {

		MongoCollection<Vaccine> vaccineCollection = getCollection();
		Bson filter = eq(VaccineConstant.VACCINE_ID, new ObjectId(vaccineId));

		return Optional.ofNullable(vaccineCollection.find(filter).first());
	}
	
	@Override
	public List<Vaccine> findAll() {
		
		MongoCollection<Vaccine> vaccineCollection = getCollection();
		return vaccineCollection.find().into(new ArrayList<Vaccine>());
	}

	@Override
	public void deleteById(String vaccineId) {
		
		Bson vaccineIdfilter = eq(VaccineConstant.VACCINE_ID, new ObjectId(vaccineId));
		MongoCollection<Vaccine> vaccineCollection = getCollection();
		vaccineCollection.deleteOne(vaccineIdfilter);
	}
	
	@Override
	public Optional<Vaccine> findByMobile(String mobile) {
		
		MongoCollection<Vaccine> vaccineCollection = getCollection();
		Bson mobileFilter = eq(VaccineConstant.MOBILE, mobile);
		return Optional.ofNullable(vaccineCollection.find(mobileFilter).first());
	}
	
	@Override
	public BsonObjectId save(Vaccine vaccine) {
		
		MongoCollection<Vaccine> vaccineCollection = getCollection();
		InsertOneResult result = vaccineCollection.insertOne(vaccine);
		return result.getInsertedId().asObjectId();
	}
	
	@Override
	public void update(Vaccine vaccine) {
		
		MongoCollection<Vaccine> vaccineCollection = getCollection();
		Bson vaccineIdFilter = eq(VaccineConstant.VACCINE_ID, vaccine.getId());
		vaccineCollection.findOneAndReplace(vaccineIdFilter, vaccine);
	}
	
	private MongoCollection<Vaccine> getCollection() {
		
		MongoDatabase database = MongoClientConfig.getDatabase();
		return database.getCollection(COLLECTION, Vaccine.class);
	}
	
}