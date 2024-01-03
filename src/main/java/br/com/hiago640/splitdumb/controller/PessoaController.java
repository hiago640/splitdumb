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

		
		pessoa.setAtivo(true);
		pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
		pessoaService.salvar(pessoa);

		redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");

		Authentication authentication = new UsernamePasswordAuthenticationToken(pessoa, null, pessoa.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/mostrarmensagem";
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
