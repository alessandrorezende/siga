package com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.siga.model.Registro;
import com.siga.repository.Perfis;
import com.siga.repository.Usuarios;

@Controller
@RequestMapping("/forgotpassword")
public class ForgotPassController {
	@Autowired
	private Usuarios usuarios;

	@Autowired
	private Perfis perfis;

	ModelAndView modelAndView = new ModelAndView("forgot-password");

	@PostMapping
	public String tryRegister(Registro r) {

		return "redirect:/forgot-password";
	}

	@GetMapping
	public ModelAndView getRegister() {
		modelAndView.addObject(new Registro());
		return modelAndView;
	}
}
