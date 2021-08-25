package com.fiap.SmartCities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fiap.SmartCities.model.VelocidadeVento;
import com.fiap.SmartCities.repository.VelocidadeVentoRepository;

@RequestMapping
@Controller
public class VelocidadeVentoController {
	
	@Autowired
	private VelocidadeVentoRepository repository;
	
	private VelocidadeVento velVento;
	
	@RequestMapping("/velocidadeVento")
	public String cadastrarUser() {
		return "velocidadeVento";
	}

}
