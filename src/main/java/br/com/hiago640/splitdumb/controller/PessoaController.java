package br.com.hiago640.splitdumb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.PessoaRepository;
import br.com.hiago640.splitdumb.service.EmailService;
import br.com.hiago640.splitdumb.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;
	
	@GetMapping("/new")
	public String abreCadastro(Pessoa pessoa) {
		LOGGER.trace("Entrou em abreCadastro");
		LOGGER.debug("encaminhando para view pessoa/newperson");

		return "pessoa/newperson";
	}

	@PostMapping("/new")
	public String cadastrarPessoa(Pessoa pessoa, RedirectAttributes redirectAttributes) {
		LOGGER.trace("Entrou em cadastrarPessoa");
		LOGGER.debug("Pessoa recebida: {}", pessoa);

		
		pessoa.setAtivo(false);
		pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
		pessoaService.salvar(pessoa);
		
		 String assunto = "Confirmação de Cadastro";
	        String corpo = "Olá " + pessoa.getNome() + ",\n\n"
	                     + "Seu cadastro foi realizado com sucesso. Por favor, clique no link abaixo para confirmar sua conta:\n\n"
	                     + "http://localhost:8080/confirmar?token=" + pessoa.getTokenConfirmacao();
	    
	    String returnEmail = emailService.sendEmail(pessoa.getEmail(), assunto, corpo);
		LOGGER.trace(returnEmail);
	    
		return "validateemail";
	}

	@GetMapping("/list")
	public ModelAndView mostrarTodasPessoas() {
		LOGGER.trace("Entrou em mostrarTodasPessoas");

		List<Pessoa> pessoas = pessoaRepository.findAll();

		LOGGER.debug("encaminhando para view pessoa/listall");
		ModelAndView mv = new ModelAndView("pessoa/listall");
		mv.addObject("pessoas", pessoas);

		return mv;
	}

}
