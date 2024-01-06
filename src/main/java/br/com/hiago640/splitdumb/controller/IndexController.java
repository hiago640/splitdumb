package br.com.hiago640.splitdumb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.hiago640.splitdumb.model.Pessoa;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@GetMapping(value = { "/", "/index.html" })
	public String index() {
		logger.trace(">>>>>>>>>>>>>>>> Entrou em index");
		logger.trace(">>>>>>>>>>>>>>>> Encaminhando para a view index");
		return "index";
	}

	@GetMapping("/mostrarmensagem")
	public String mostrarMensagem() {
		logger.trace("Entrou em mostrarMensagem");
		logger.trace("Encaminhando para a view mostrarmensagem");
		return "mostrarmensagem";
	}

	@GetMapping("/login")
	public String login(Pessoa pessoa) {
		logger.trace("Entrou em login");
		logger.trace("Encaminhando para a view login");
		return "login";
	}

}