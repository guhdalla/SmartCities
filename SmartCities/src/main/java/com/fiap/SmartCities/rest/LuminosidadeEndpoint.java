package com.fiap.SmartCities.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fiap.SmartCities.model.Luminosidade;
import com.fiap.SmartCities.repository.LuminosidadeRepository;


@Path("/luminosidades")
@RestController
public class LuminosidadeEndpoint {
	
	
	LuminosidadeRepository repo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Luminosidade> luminosidade() {
		return repo.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Luminosidade> create(@RequestBody Luminosidade luminosidade, UriComponentsBuilder uriBuilder) {
		repo.save(luminosidade);
		URI uri = uriBuilder.path("/luminosidades/{id}").buildAndExpand(luminosidade.getId()).toUri();
		return ResponseEntity.created(uri).body(luminosidade);
	}
}