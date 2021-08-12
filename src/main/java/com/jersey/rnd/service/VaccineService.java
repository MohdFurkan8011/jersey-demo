package com.jersey.rnd.service;

import java.util.List;
import java.util.Optional;

import org.bson.BsonObjectId;
import org.jvnet.hk2.annotations.Contract;

import com.jersey.rnd.model.Vaccine;

@Contract
public interface VaccineService {

	public Optional<Vaccine> findById(String vaccineId);
	
	public List<Vaccine> findAll();
	
	public Optional<Vaccine> findByMobile(String mobile);
	
	public BsonObjectId save(Vaccine vaccine);
	
	public void update(Vaccine vaccine);
	
	public void deleteById(String vaccineId);
	
}
