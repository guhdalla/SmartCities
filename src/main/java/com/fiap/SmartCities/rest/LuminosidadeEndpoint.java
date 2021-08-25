package com.fiap.SmartCities.rest;

import java.util.List; 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.SmartCities.model.Luminosidade;
import com.fiap.SmartCities.repository.LuminosidadeRepository;


@Path("/luminosidaes")
@RestController
public class LuminosidadeEndpoint {
	
	
	LuminosidadeRepository repo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Luminosidade> luminosidade() {
		return repo.findAll();
	}
}