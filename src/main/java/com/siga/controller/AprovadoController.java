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

import com.siga.model.Aluno;
import com.siga.model.Instituicao;
import com.siga.repository.Alunos;
import com.siga.repository.Instituicoes;
import com.siga.rest.RequestHandler;

@Controller
@RequestMapping("/solicitarAprovados")
public class AprovadoController {

	private static final String nomeIntituicao = "Puc Minas";
	private static final Logger log = LoggerFactory.getLogger(AprovadoController.class);

	@Autowired
	private Alunos alunos;
	@Autowired
	private Instituicoes instiuicoes;

	private RestTemplate restTemplate = new RestTemplate();

	ModelAndView modelAndView = new ModelAndView("solicitar-aprovados");

	@GetMapping
	public ModelAndView aprovadosView() {
		modelAndView.addObject("aprovados", alunos.findByAprovadoProuni(true));
		return modelAndView;
	}

	@PostMapping
	public String findAprovados() {
		try {
			log.info("Enviando requisição aprovados PROUNI.");

			Instituicao instituicao = instiuicoes.findByNome(nomeIntituicao);
			ResponseEntity<Aluno[]> aprovadosList = restTemplate.exchange(
					RequestHandler.getURIAprovados() + "?instituicao=" + instituicao.getNome() + "&hashid="
							+ instituicao.getHashId(),
					HttpMethod.GET,
					new HttpEntity<Aluno>(RequestHandler.createHeaders(instituicao.getNome(), instituicao.getHashId())),
					Aluno[].class);

			for (Aluno aprovado : aprovadosList.getBody()) {
				log.info("Salvando aprovado PROUNI enviada pelo MEC.");
				aprovado.setAprovadoProuni(true);
				alunos.save(aprovado);
			}
			modelAndView.addObject("msgSucesso", "Busca realizada com sucesso!");
			modelAndView.addObject("msgErro", "");
		} catch (Exception e) {
			log.error("Erro ao fazer a busca: \n" + e.getMessage());
			modelAndView.addObject("msgErro", "Erro ao fazer a busca: \n" + e.getMessage());
			modelAndView.addObject("msgSucesso", "");
		}

		return "redirect:/solicitarAprovados";
	}

}
