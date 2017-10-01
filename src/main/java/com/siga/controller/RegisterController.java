package com.siga.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.siga.model.Perfil;
import com.siga.model.Registro;
import com.siga.model.Usuario;
import com.siga.repository.Perfis;
import com.siga.repository.Usuarios;
import com.siga.util.GenerateHashPasswordUtil;

@Controller
@RequestMapping("/register")
public class RegisterController {

	ModelAndView modelAndView = new ModelAndView("register");

	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

	@Autowired
	private Usuarios usuarios;

	@Autowired
	private Perfis perfis;

	@Autowired
	public RegisterController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
		this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
	}

	public boolean userExistsInMemory(Usuario u) {
		return inMemoryUserDetailsManager.userExists(u.getUsername());
	}

	public void addUserInMemory(Usuario u) {
		inMemoryUserDetailsManager
				.createUser(new User(u.getUsername(), u.getPassword(), new ArrayList<GrantedAuthority>()));
	}

	@PostMapping
	public String registrarUsuario(Registro r) {
		if (r != null && r.getPasswordConfirm().equals(r.getUsuario().getPassword())) {
			Usuario u = usuarios.findByUsernameAndPassword(r.getUsuario().getUsername(), r.getUsuario().getPassword());
			if (u == null) {
				Perfil p = perfis.findByNome(r.getPerfil().getNome());
				if (p == null) {
					modelAndView.getModelMap().addAttribute("msgErro", "Perfil não existe!");
				} else {
					r.getUsuario().setPerfil(p);
					r.getUsuario().setPassword(GenerateHashPasswordUtil.generateHash(r.getUsuario().getPassword()));
					addUserInMemory(r.getUsuario());
					usuarios.save(r.getUsuario());
					modelAndView.getModelMap().clear();
					modelAndView.getModelMap().addAttribute("msgSucesso", "Usuário cadastrado com sucesso!");
					modelAndView.getModelMap().addAttribute("msgErro", "");
				}
			} else {
				modelAndView.getModelMap().addAttribute("msgErro", "Usuário já cadastrado!");
				modelAndView.getModelMap().addAttribute("msgSucesso", "");
			}
		} else {
			modelAndView.getModelMap().addAttribute("msgErro", "Informe todos os campos!");
			modelAndView.getModelMap().addAttribute("msgSucesso", "");
		}

		return "redirect:/register";
	}

	@GetMapping
	public ModelAndView getViewRegiter() {
		modelAndView.addObject(new Registro());
		modelAndView.getModelMap().addAttribute("perfilList", perfis.findAll());
		return modelAndView;
	}
}
