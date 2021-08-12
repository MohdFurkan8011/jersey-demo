package com.jersey.rnd.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.bson.BsonObjectId;
import org.jvnet.hk2.annotations.Service;

import com.jersey.rnd.dao.VaccineDAO;
import com.jersey.rnd.model.Vaccine;
import com.jersey.rnd.service.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService {

	@Inject
	private VaccineDAO vaccineDAO;
	
	@Override
	public Optional<Vaccine> findById(String vaccineId) {
		
		return vaccineDAO.findById(vaccineId);
	}
	
	@Override
	public List<Vaccine> findAll() {
		
		return vaccineDAO.findAll();
	}
	
	@Override
	public BsonObjectId save(Vaccine vaccine) {
		
		return vaccineDAO.save(vaccine);
	}
	
	@Override
	public void update(Vaccine vaccine) {
		
		vaccineDAO.update(vaccine);
	}
	
	
	@Override
	public void deleteById(String vaccineId) {
		
		vaccineDAO.deleteById(vaccineId);
	}
	
	@Override
	public Optional<Vaccine> findByMobile(String mobile) {
		
		return vaccineDAO.findByMobile(mobile);
	}

}
