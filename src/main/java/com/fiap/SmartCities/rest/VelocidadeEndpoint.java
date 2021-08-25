package com.fiap.SmartCities.rest;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.SmartCities.model.VelocidadeVento;
import com.fiap.SmartCities.repository.VelocidadeVentoRepository;


@RestController
public class VelocidadeEndpoint {
	
	@Autowired
	VelocidadeVentoRepository repo;
	
//	@GET
//	public List<VelocidadeVento> index() {
//		return repo.findAll();
//	}
	
	@GetMapping("/velocidades")
	public List<VelocidadeVento> index() {
		return repo.findAll();
	}
}