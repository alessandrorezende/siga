package com.siga.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.siga.model.Curso;
import com.siga.model.Instituicao;
import com.siga.repository.Cursos;
import com.siga.repository.Instituicoes;
import com.siga.rest.RequestHandler;

@Controller
@RequestMapping("/vagasOciosas")
public class VagaOciosaController {

	private static final String nomeIntituicao = "Puc Minas";
	private static final Logger log = LoggerFactory.getLogger(VagaOciosaController.class);

	@Autowired
	private Cursos cursos;
	@Autowired
	private Instituicoes instiuicoes;

	ModelAndView modelAndView = new ModelAndView("vagas-ociosas");

	@GetMapping
	public ModelAndView vagasOciosasView() {
		modelAndView.addObject("cursosVagasOciosas", cursos.findByQueryVagasOciosas());
		return modelAndView;
	}

	@PostMapping
	public String enviarVagasOciosas() {
		try {
			log.info("Enviando requisição vagas ociosas por curso.");

			RestTemplate restTemplate = new RestTemplate();

			Instituicao instituicao = instiuicoes.findByNome(nomeIntituicao);
			// header
			HttpHeaders headers = RequestHandler.createHeaders(instituicao.getNome(), instituicao.getHashId());
			headers.setContentType(MediaType.APPLICATION_JSON);

			List<Curso> cursoList = cursos.findByQueryVagasOciosas();
			HttpEntity<List<Curso>> httpEntity = new HttpEntity<List<Curso>>(cursoList, headers);

			Curso[] response = restTemplate.postForObject(RequestHandler.getURIVagasOciosas(), httpEntity,
					Curso[].class);

			if (response != null) {
				modelAndView.addObject("msgSucesso", "Enviado notas com sucesso!");
				modelAndView.addObject("msgErro", "");
			} else {
				modelAndView.addObject("msgErro", "Erro ao enviar as notas!");
				modelAndView.addObject("msgSucesso", "");
			}
		} catch (

		Exception e) {
			log.error("Erro ao fazer a busca: \n" + e.getMessage());
			modelAndView.addObject("msgErro", "Erro ao enviar vagas ociosas: \n" + e.getMessage());
			modelAndView.addObject("msgSucesso", "");
		}

		return "redirect:/vagasOciosas";
	}
}
