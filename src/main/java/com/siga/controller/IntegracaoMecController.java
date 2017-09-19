package com.siga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IntegracaoMecController {

	@GetMapping("/vagasOciosas")
	public ModelAndView vagasOciosas() {
		ModelAndView modelAndView = new ModelAndView("vagasOciosas");
		return modelAndView;
	}

	@GetMapping("/solicitarAprovados")
	public ModelAndView solicitarAprovados() {
		ModelAndView modelAndView = new ModelAndView("solicitarAprovados");
		return modelAndView;
	}

	@GetMapping("/solicitarNotas")
	public ModelAndView solicitarNotas() {
		ModelAndView modelAndView = new ModelAndView("solicitarNotas");
		return modelAndView;
	}

}
