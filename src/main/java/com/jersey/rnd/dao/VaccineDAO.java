package com.jersey.rnd.dao;

import java.util.List;
import java.util.Optional;

import org.bson.BsonObjectId;
import org.jvnet.hk2.annotations.Contract;

import com.jersey.rnd.model.Vaccine;

@Contract
public interface VaccineDAO {

	public static final String COLLECTION = "vaccine";
	
	public List<Vaccine> findAll();
	public void deleteById(String vaccineId);
	public Optional<Vaccine> findById(String vaccineId);
	public Optional<Vaccine> findByMobile(String mobile);
	public BsonObjectId save(Vaccine vaccine);
	public void update(Vaccine vaccine);
	
}
