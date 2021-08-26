package com.fiap.SmartCities.rest;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fiap.SmartCities.model.Temperatura;
import com.fiap.SmartCities.repository.TemperaturaRepository;


@Path("/temperaturas")
@Produces(MediaType.APPLICATION_JSON)
public class TemperaturaEndpoint {
	
	TemperaturaRepository repo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temperatura> temp() {
		return repo.findAll();
	}
	
	@PostMapping("/temperaturas")
	public Temperatura addTemp(Temperatura temp) {
		return repo.save(temp);
	}
	
	
}