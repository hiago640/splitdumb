package br.com.hiago640.splitdumb.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.CompraRepository;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/splitdumb/compras")
public class CompraController {

	private static final Logger logger = LoggerFactory.getLogger(CompraController.class);

	@Autowired
	private GrupoRepository grupoRepository;

	@GetMapping("/buscar/")
	public Set<Compra> buscaComprasPorGrupo(@RequestParam("idGrupo") Long idGrupo) {

		logger.info("entrou em buscaComprasPorGrupo");
		logger.info("idGrupo recebido: {}", idGrupo);

		Grupo grupo = grupoRepository.buscarComComprasByID(idGrupo);

		logger.info("grupo localizado: {}", grupo);
		logger.info("grupo com compras localizados: {}", grupo.getCompras());

		return grupo.getCompras();
	}

	@PostMapping("/criar")
	public Compra novaCompra(@Valid Compra compra) {

		logger.info("entrou em novaCompra");

		Grupo grupo = compra.getGrupo();
		grupo.getCompras().add(compra);

		logger.info("compra criada: {}", compra);
		grupoRepository.save(grupo);

		return compra;
	}
}
