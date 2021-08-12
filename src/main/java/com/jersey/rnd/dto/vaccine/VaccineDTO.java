package com.jersey.rnd.dto.vaccine;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VaccineDTO {

	private String registrationId;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String mobile;
	
	private String boothPlace;
	
	private String doseType;
	
	private Byte doseNo;
	
	private boolean isDoseDone;
	
	private LocalDate firstDoseRegistrationDate;
	
	private LocalDate firstDoseDate;

	private LocalDate secondDoseRegistrationDate;
	
	private LocalDate secondDoseDate;
	
}