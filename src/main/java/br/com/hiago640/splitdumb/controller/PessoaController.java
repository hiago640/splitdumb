package br.com.hiago640.splitdumb.controller;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import br.com.hiago640.splitdumb.repository.PessoaRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("api/splitdumb/pessoas")
public class PessoaController {

	private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@GetMapping("/")
	public List<Pessoa> buscarPessoas() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{id}")
	public List<Pessoa> buscarPessoasByID(@PathVariable("id") long id) {
		return pessoaRepository.findAll();
	}

	@PostMapping("/")
	public ModelAndView create(Pessoa pessoa) {
		logger.info("Entrou em create pessoa");

		logger.info("pessoa recebida {}", pessoa.getNome());

		ModelAndView model = new ModelAndView("pessoa/cadastrapessoa");
		model.addObject("pessoa", pessoa);

		pessoaRepository.save(pessoa);

		return model;
	}

	@PostMapping("/join")
	public Pessoa joinGrupo(
			@RequestParam(name = "idPessoa") long idPessoa,
			@RequestParam(name = "idGrupo") long idGrupo) {
		Pessoa pessoa = pessoaRepository.findById(idPessoa).orElse(null);
		Grupo grupo = grupoRepository.buscarComComprasByID(idGrupo);

		if (pessoa != null && grupo != null) {
			if (!pessoa.getGrupos().contains(grupo)) {
				pessoa.getGrupos().add(grupo);
				pessoaRepository.save(pessoa);
			}
		}

		return pessoa;
	}
}
