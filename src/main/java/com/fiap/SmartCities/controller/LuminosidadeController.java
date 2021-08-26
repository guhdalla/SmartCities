package com.fiap.SmartCities.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.SmartCities.model.Luminosidade;
import com.fiap.SmartCities.model.VelocidadeVento;
import com.fiap.SmartCities.repository.LuminosidadeRepository;

@Controller
public class LuminosidadeController {
	
	@RequestMapping("/luminosidade")
	public String index() {
		return "luminosidade";
	}
	
}
