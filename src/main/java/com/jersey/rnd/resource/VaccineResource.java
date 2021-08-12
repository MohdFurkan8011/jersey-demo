package com.jersey.rnd.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.BsonObjectId;

import com.jersey.rnd.dto.vaccine.LoadBalance;
import com.jersey.rnd.dto.vaccine.VaccineDTO;
import com.jersey.rnd.mapper.VaccineMapper;
import com.jersey.rnd.model.Vaccine;
import com.jersey.rnd.param.SecondDoseParam;
import com.jersey.rnd.param.VaccineParam;
import com.jersey.rnd.service.VaccineService;

@Path("vaccine")
public class VaccineResource {
	
	@Inject
	private VaccineService vaccineService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		
		List<Vaccine> vaccineList = vaccineService.findAll();
		List<VaccineDTO> vaccineDTOList = VaccineMapper.mapToVaccineDTOList(vaccineList);
		
		LoadBalance loadBalance = new LoadBalance();
		loadBalance.setPort("8081");
		loadBalance.setVaccineDTOs(vaccineDTOList);
		return Response.ok(loadBalance).build();
	}
	
	@GET
	@Path(value = "{vaccineId}")
	@Produces
	public Response findById(@PathParam("vaccineId") String vaccineId) {
		
		Optional<Vaccine> vaccineOpt = vaccineService.findById(vaccineId);
		if (vaccineOpt.isPresent()) {
			VaccineDTO vaccineDTO = VaccineMapper.mapToVaccineDTO(vaccineOpt.get());
			vaccineDTO.setRegistrationId(vaccineId);
			return Response.status(Status.FOUND).entity(vaccineDTO).build();
		} else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(VaccineParam vaccineParam) {
		
		Optional<Vaccine> vaccineOpt = vaccineService.findByMobile(vaccineParam.getMobile());
		
		if (vaccineOpt.isPresent())
			return Response.status(Status.CONFLICT).build();
		
		Vaccine vaccine = VaccineMapper.mapToVaccine(vaccineParam);
		vaccine.setFirstDoseRegistrationDate(LocalDate.now());
		vaccine.setDoseNo(new Byte("1"));
		vaccine.setDoseDone(false);
		
		BsonObjectId objectId = vaccineService.save(vaccine);
		VaccineDTO vaccineDTO = VaccineMapper.mapToVaccineDTO(vaccine);
		vaccineDTO.setRegistrationId(objectId.getValue().toString());
		
		return Response.status(Status.CREATED).entity(vaccineDTO).build();
	}
	
	@PUT
	@Path("{vaccineId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("vaccineId") String vaccineId, VaccineParam vaccineParam) {
		
		Optional<Vaccine> vaccineOpt = vaccineService.findById(vaccineId);
		vaccineOpt.ifPresent(vaccine -> {
			if (vaccine.getDoseNo() == 1 && !vaccine.isDoseDone()) {
				vaccine = VaccineMapper.mapToVaccine(vaccineParam, vaccine);
				vaccineService.update(vaccine);
			}
		});
		
		if (vaccineOpt.isPresent()) {
		
			VaccineDTO vaccineDTO = VaccineMapper.mapToVaccineDTO(vaccineOpt.get());
			vaccineDTO.setRegistrationId(vaccineId);
			return Response.ok(vaccineDTO).build();
		}		
		return Response.status(Status.NOT_MODIFIED).build();
	}
	
	@DELETE
	@Path(value = "/{vaccineId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("vaccineId") String vaccineId) {
		
		Optional<Vaccine> vaccineOpt = vaccineService.findById(vaccineId);
		if (vaccineOpt.isPresent()) {
			vaccineService.deleteById(vaccineId);
			return Response.ok().build();	
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@PATCH
	@Path(value = "/taken/{vaccineId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doseTaken(@PathParam("vaccineId") String vaccineId) {
		
		Optional<Vaccine> vaccineOpt = vaccineService.findById(vaccineId);
		if (vaccineOpt.isPresent()) {
			
			Vaccine vaccine = vaccineOpt.get();
			vaccine.setDoseDone(true);
			vaccineService.update(vaccine);
			return Response.ok().build();
		}		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@PATCH
	@Path(value = "/second-dose/{vaccineId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerForSecondDose(@PathParam("vaccineId") String vaccineId, SecondDoseParam secondDoseParam) {
		
		Optional<Vaccine> vaccineOpt = vaccineService.findById(vaccineId);
		if (vaccineOpt.isPresent()) {
			Vaccine vaccine = vaccineOpt.get();
			vaccine.setSecondDoseDate(secondDoseParam.getSecondDoseDate());
			vaccine.setSecondDoseRegistrationDate(LocalDate.now());
			vaccine.setDoseDone(false);
			vaccineService.update(vaccine);
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
}