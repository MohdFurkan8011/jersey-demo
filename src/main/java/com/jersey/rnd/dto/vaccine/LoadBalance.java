package com.jersey.rnd.dto.vaccine;

import java.util.List;

import lombok.Data;

@Data
public class LoadBalance {
	
	private String port;
	private List<VaccineDTO> vaccineDTOs;

}
