package com.fiap.SmartCities.rest;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fiap.SmartCities.model.Temperatura;
import com.fiap.SmartCities.repository.TemperaturaRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Path("/temperaturas")
@Produces(MediaType.APPLICATION_JSON)
public class TemperaturaEndpoint {
	
	TemperaturaRepository repo;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Temperatura> temp() {
		return repo.findAll();
	}
}