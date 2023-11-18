package br.com.hiago640.splitdumb.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import br.com.hiago640.splitdumb.repository.PessoaRepository;
import br.com.hiago640.splitdumb.service.PessoaService;

@RestController
@RequestMapping("api/splitdumb/pessoas")
public class PessoaController {

	private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private PessoaService pessoaService;

	@GetMapping("/")
	public List<Pessoa> buscarPessoas() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Pessoa> buscarPessoasByID(@PathVariable("id") long id) {
		return pessoaRepository.findById(id);
	}

	@PostMapping("/")
	public RedirectView create(Pessoa pessoa, RedirectAttributes redirectAttributes) {
		logger.info("Entrou em create pessoa");
		logger.info("pessoa recebida {}", pessoa.getNome());
		pessoaService.salvar(pessoa);

		String mensagem = String.format("A Pessoa %s foi cadastrada com sucesso!", pessoa.getNome());
		redirectAttributes.addFlashAttribute("mensagem", mensagem);
		logger.trace("Redirecionando para a URL /mostrarmensagem");
		return new RedirectView("/mostrarmensagem");
	}

	@PostMapping("/join/{idPessoa}-{idGrupo}")
	public Pessoa joinGrupo(@PathVariable("idPessoa") long idPessoa, @PathVariable("idGrupo") long idGrupo) {
		logger.info("entrou em joinGrupo");
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
