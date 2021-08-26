package com.fiap.SmartCities.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LuminosidadeController {

	@RequestMapping("/luminosidade")
	public ModelAndView index() {
		ModelAndView m = new ModelAndView("luminosidade");
		return m;
	}

}
