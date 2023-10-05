package br.com.hiago640.splitdumb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import br.com.hiago640.splitdumb.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/splitdumb/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@GetMapping("/buscar")
	public List<Pessoa> buscarPessoas() {
		return pessoaRepository.findAll();
	}

	@PostMapping("/criar")
	public Pessoa novaPessoa(@Valid Pessoa pessoa) {
		pessoaRepository.save(pessoa);

		return pessoa;
	}

	@PostMapping("/join")
	public Pessoa joinGrupo(@RequestParam(name = "idPessoa") long idPessoa,
			@RequestParam(name = "idGrupo") long idGrupo) {
		Pessoa pessoa = pessoaRepository.findById(idPessoa).orElse(null);
		Grupo grupo = grupoRepository.findById(idGrupo).orElse(null);

		if (pessoa != null && grupo != null) {

			if (!pessoa.getGrupos().contains(grupo)) {
				pessoa.getGrupos().add(grupo);
				pessoaRepository.save(pessoa);
			}

		}
		return pessoa;
	}

}