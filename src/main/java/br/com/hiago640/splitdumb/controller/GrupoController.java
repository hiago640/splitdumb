package br.com.hiago640.splitdumb.controller;

import java.util.List;

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

	@Autowired
	private GrupoRepository grupoRepository;
	
	@GetMapping("/buscar")
	public List<Grupo> buscarGrupos(){
		return grupoRepository.findAll();
	}
	
	@PostMapping("/criar")
	public Grupo novoGrupo(@Valid Grupo grupo){
		grupoRepository.save(grupo);
		
		return grupo;
	}
	
}
