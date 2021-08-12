package com.jersey.rnd.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.jersey.rnd.dto.vaccine.VaccineDTO;
import com.jersey.rnd.model.Vaccine;
import com.jersey.rnd.param.VaccineParam;

public class VaccineMapper {

	private static final ModelMapper mapper;
	
	private VaccineMapper() {}
	
	static {
		mapper = new ModelMapper();
	}
	
	public static Vaccine mapToVaccine(VaccineParam vaccineParam) {
		
		return mapping(vaccineParam, new Vaccine());
	}
	
	public static Vaccine mapToVaccine(VaccineParam vaccineParam, Vaccine vaccine) {
		
		return mapping(vaccineParam, vaccine);
	}
	
	public static VaccineDTO mapToVaccineDTO(Vaccine vaccine) {
		
		return mapper.map(vaccine, VaccineDTO.class);
	}
	
	public static List<VaccineDTO> mapToVaccineDTOList(List<Vaccine> vaccines) {
		
		return vaccines.stream().map(vaccine -> {
			VaccineDTO vaccineDTO = mapToVaccineDTO(vaccine);
			vaccineDTO.setRegistrationId(vaccine.getId().toString());
			return vaccineDTO;
		}).collect(Collectors.toList());
	}
	
	private static Vaccine mapping(VaccineParam vaccineParam, Vaccine vaccine) {
		
		mapper.map(vaccineParam, vaccine);
		return vaccine;
	}
	
	
}
