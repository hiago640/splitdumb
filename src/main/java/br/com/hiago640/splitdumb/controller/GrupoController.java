package br.com.hiago640.splitdumb.controller;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import br.com.hiago640.splitdumb.service.GrupoService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("api/splitdumb/grupos")
public class GrupoController {

	private static final Logger logger = LoggerFactory.getLogger(
			GrupoController.class);

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoService grupoService;

	@GetMapping("/")
	public List<Grupo> buscarGrupos() {
		logger.info("entrou em buscarGrupos");
		return grupoRepository.buscarComCompras();
	}

	@GetMapping("/{id}")
	public Optional<Grupo> buscarGrupo(@PathVariable("id") long id) {
		logger.info("entrou em buscarGrupo");
		return grupoRepository.findById(id);
	}

	@PostMapping("/")
	public RedirectView create(Grupo grupo, RedirectAttributes redirectAttributes) {
		logger.info("Entrou em create grupo");
		logger.info("grupo recebido {}", grupo.getNome());
		grupoService.salvar(grupo);

		String mensagem = String.format("O Grupo %s foi cadastrado com sucesso!", grupo.getNome());
		redirectAttributes.addFlashAttribute("mensagem", mensagem);
		logger.trace("Redirecionando para a URL /mostrarmensagem");
		return new RedirectView("/mostrarmensagem");
	}
}
