package com.siga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.siga.model.Instituicao;
import com.siga.model.Nota;
import com.siga.repository.Instituicoes;
import com.siga.repository.Notas;
import com.siga.rest.RequestHandler;

@Controller
@RequestMapping("/solicitarNotas")
public class NotaController {

	private static final String nomeIntituicao = "Puc Minas";
	private static final Logger log = LoggerFactory.getLogger(NotaController.class);

	@Autowired
	private Notas notas;
	@Autowired
	private Instituicoes instiuicoes;

	private RestTemplate restTemplate = new RestTemplate();

	ModelAndView modelAndView = new ModelAndView("solicitar-notas");

	@GetMapping
	public ModelAndView notasCPCView() {
		modelAndView.addObject("notas", notas.findAll());
		return modelAndView;
	}

	@PostMapping
	public String findNotasCPC() {
		try {
			log.info("Enviando requisição Notas CPC.");

			Instituicao instituicao = instiuicoes.findByNome(nomeIntituicao);
			ResponseEntity<Nota[]> notasList = restTemplate.exchange(
					RequestHandler.getURINotas() + "?instituicao=" + instituicao.getNome() + "&hashid="
							+ instituicao.getHashId(),
					HttpMethod.GET,
					new HttpEntity<Nota>(RequestHandler.createHeaders(instituicao.getNome(), instituicao.getHashId())),
					Nota[].class);

			for (Nota nota : notasList.getBody()) {
				log.info("Salvando Notas CPC enviada pelo MEC.");
				notas.save(nota);
			}
			modelAndView.addObject("msgSucesso", "Busca realizada com sucesso!");
			modelAndView.addObject("msgErro", "");
		} catch (Exception e) {
			log.error("Erro ao fazer a busca: \n" + e.getMessage());
			modelAndView.addObject("msgErro", "Erro ao fazer a busca: \n" + e.getMessage());
			modelAndView.addObject("msgSucesso", "");
		}

		return "redirect:/solicitarNotas";
	}

}
