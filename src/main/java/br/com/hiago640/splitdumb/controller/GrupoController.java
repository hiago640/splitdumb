package br.com.hiago640.splitdumb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/splitdumb/grupos")
public class GrupoController {

	private static final Logger logger = LoggerFactory.getLogger(GrupoController.class);
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@GetMapping("/buscar")
	public List<Grupo> buscarGrupos(){
		logger.info("entrou em buscarGrupos");
		return grupoRepository.buscarComCompras();
	}
	
	@PostMapping("/criar")
	public Grupo novoGrupo(@Valid Grupo grupo){
		grupoRepository.save(grupo);
		
		return grupo;
	}
	
//	@GetMapping("/buscar/")
//	public List<Pessoa> buscarID(@RequestParam(name = "id") long id) {
//		Grupo grupo = grupoRepository.findById(id).orElse(null);
//		
//		 List<Pessoa> integrantesDoGrupo = pessoaRepository.findByGruposContaining(grupo);
//		 
//		 return integrantesDoGrupo;
//	}
	
}
