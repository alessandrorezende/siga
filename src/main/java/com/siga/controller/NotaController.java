package com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.siga.model.Convidado;
import com.siga.model.Nota;
import com.siga.repository.Notas;

@Controller
@RequestMapping("/notas")
public class NotaController {

	private static final String nomeIntituicao = "PucMinas";
	private static final String hashID = "abc123";
	private static final String URI = "http://localhost:5000/cursos";
	@Autowired
	private Notas notas;

	private RestTemplate restTemplate = new RestTemplate();
	
	@PostMapping
	public String findNotasCPC() {
		String URL = URI + "?instituicao=" + nomeIntituicao + "&hashid=" + hashID;
		ResponseEntity<Nota[]> responseEntity = restTemplate.getForEntity(URL, Nota[].class);
		HttpStatus statusCode = responseEntity.getStatusCode();

		if (statusCode.OK != null) {
			Nota[] notasList = responseEntity.getBody();
			for (Nota nota : notasList) {
				if(!notas.exists(nota.getId())) {
					notas.save(nota);
				}
			}
		}
		
		return "redirect:/notas";
	}

	@GetMapping
	public ModelAndView listarNotasCPC() {
		ModelAndView modelAndView = new ModelAndView("ListaNotas");
		modelAndView.addObject("notas", notas.findAll());
		return modelAndView;
	}

}
