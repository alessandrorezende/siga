package com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siga.model.Usuario;
import com.siga.repository.Usuarios;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private Usuarios usuarios;

	
	@PostMapping
	public String tryLogin(Usuario usuario) {
		Usuario u = usuarios.findByUsernameAndPassword(usuario.getUsername(),usuario.getPassword());
		if(u != null) {
			return "redirect:/index";
		}
		return "login";
	}
}
