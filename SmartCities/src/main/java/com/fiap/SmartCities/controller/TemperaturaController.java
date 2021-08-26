package com.fiap.SmartCities.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.SmartCities.model.Temperatura;
import com.fiap.SmartCities.model.VelocidadeVento;
import com.fiap.SmartCities.repository.TemperaturaRepository;

@Controller
public class TemperaturaController {
	
	@Autowired
	TemperaturaRepository repositoryTemp;
	
	@RequestMapping("/temperatura")
	public String index() {
		return "temperatura";
	}
	
	public void create(Double temperatura) {
		Temperatura t = new Temperatura();
		t.setId(1L);
		t.setTemperatura(temperatura);
		t.setDATASINAL("05:05:05");
		repositoryTemp.save(t);
	}
}
