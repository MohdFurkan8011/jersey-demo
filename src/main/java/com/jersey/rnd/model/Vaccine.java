package com.jersey.rnd.model;

import java.time.LocalDate;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Vaccine {

	private ObjectId id;
	
	private String registrationId;
	
	@BsonProperty("first_name")
	private String firstName;
	
	@BsonProperty("last_name")
	private String lastName;
	
	@BsonProperty("address")
	private String address;
	
	@BsonProperty("mobile")
	private String mobile;
	
	@BsonProperty("booth_place")
	private String boothPlace;
	
	@BsonProperty("dose_type")
	private String doseType;
	
	@BsonProperty("dose_no")
	private Byte doseNo;
	
	@BsonProperty("is_dose_done")
	private boolean isDoseDone;
	
	@BsonProperty("first_dose_registration_date")
	private LocalDate firstDoseRegistrationDate;
	
	@BsonProperty("first_dose_date")
	private LocalDate firstDoseDate;

	@BsonProperty("second_dose_registration_date")
	private LocalDate secondDoseRegistrationDate;
	
	@BsonProperty("second_dose_date")
	private LocalDate secondDoseDate;
	
}