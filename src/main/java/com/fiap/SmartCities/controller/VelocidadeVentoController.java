package com.fiap.SmartCities.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fiap.SmartCities.model.*;
import com.fiap.SmartCities.repository.VelocidadeVentoRepository;

@Controller
public class VelocidadeVentoController {
	
	@RequestMapping("/velocidade-vento")
	public String index() {
		return "velocidadeVento";
	}
}
