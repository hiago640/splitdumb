package br.com.hiago640.splitdumb.controller;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
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

@RestController
@RequestMapping("api/splitdumb/grupos")
public class GrupoController {

	private static final Logger logger = LoggerFactory.getLogger(
			GrupoController.class);

	@Autowired
	private GrupoRepository grupoRepository;

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
	public ModelAndView pagina(Grupo grupo) {
		logger.info("grupo recebido {}", grupo.getNome());

		ModelAndView model = new ModelAndView("grupo/cadastragrupo");
		model.addObject("grupo", grupo);

		grupoRepository.save(grupo);

		return model;
	}
}
