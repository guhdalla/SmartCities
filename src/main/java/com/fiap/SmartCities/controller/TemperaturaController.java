package com.fiap.SmartCities.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fiap.SmartCities.model.*;
import com.fiap.SmartCities.repository.TemperaturaRepository;
import com.google.gson.Gson;

@Controller
public class TemperaturaController {
	
	@Autowired
	TemperaturaRepository repo;
	
	@RequestMapping("/temp")
	public String index() {
		return "temperatura";
	}
	
	@PostMapping("/temp")
	public Gson temperatura() {
		List<Temperatura> tem = repo.findAll();
		Gson json = new Gson();
		json.toJson(tem);
		System.out.println(json.toString());
		return json;
	}
}
