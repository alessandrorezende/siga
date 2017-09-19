package com.siga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.siga.model.Usuario;

@Controller
@SpringBootApplication
public class SigaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigaApplication.class, args);
	}
	
	
	@RequestMapping("/")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject(new Usuario());
		return modelAndView;
	}
	

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject(new Usuario());
		return modelAndView;
	}
}
