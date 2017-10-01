package com.siga.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.siga.model.Convidado;
import com.siga.model.Instituicao;
import com.siga.model.Nota;
import com.siga.model.Usuario;
import com.siga.repository.Instituicoes;
import com.siga.repository.Notas;
import com.siga.repository.Usuarios;
import com.siga.rest.RequestHandler;

@Controller
@RequestMapping("/notas")
public class NotaController {

	private static final String nomeIntituicao = "Puc Minas";
	private static final Logger log = LoggerFactory.getLogger(NotaController.class);

	@Autowired
	private Notas notas;
	@Autowired
	private Instituicoes instiuicoes;
	@Autowired
	private Usuarios usuarios;

	private RestTemplate restTemplate = new RestTemplate();

	@PostMapping
	public String findNotasCPC() {
		try {
			Instituicao instituicao = instiuicoes.findByNome(nomeIntituicao);
			Usuario usuario = usuarios.getOne(1l);
			ResponseEntity<Nota[]> notasList = restTemplate.exchange(
					RequestHandler.getURI() + "?instituicao=" + instituicao.getNome() + "&hashid="
							+ usuario.getHashId(),
					HttpMethod.GET, new HttpEntity<Nota>(RequestHandler.createHeaders("alessandro", "123")),
					Nota[].class);

			for (Nota nota : notasList.getBody()) {
				notas.save(nota);
			}
		} catch (Exception e) {

		}

		return "redirect:/notas";
	}

	@GetMapping
	public ModelAndView listarNotasCPC() {
		ModelAndView modelAndView = new ModelAndView("solicitarNotas");
		modelAndView.addObject("notas", notas.findAll());
		return modelAndView;
	}

}
