package com.jersey.rnd.param;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VaccineParam {

	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String mobile;
	
	private String boothPlace;
	
	private String doseType;
	
	private LocalDate firstDoseDate;

}