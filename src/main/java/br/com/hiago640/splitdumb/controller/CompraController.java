package br.com.hiago640.splitdumb.controller;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.CompraRepository;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import br.com.hiago640.splitdumb.service.GrupoService;
import jakarta.validation.Valid;
import java.util.Set;
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
@RequestMapping("api/splitdumb/compras")
public class CompraController {

	private static final Logger logger = LoggerFactory.getLogger(
			CompraController.class);

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoService grupoService;

	@GetMapping("/{idGrupo}")
	public Set<Compra> buscaComprasPorGrupo(
			@PathVariable("idGrupo") long idGrupo) {
		logger.info("entrou em buscaComprasPorGrupo");
		logger.info("idGrupo recebido: {}", idGrupo);

		Grupo grupo = grupoRepository.buscarComComprasByID(idGrupo);

		logger.info("grupo localizado: {}", grupo);
		logger.info("grupo com compras localizados: {}", grupo.getCompras());

		return grupo.getCompras();
	}

	@PostMapping("/")
	public RedirectView create(@Valid Compra compra, RedirectAttributes redirectAttributes) {
		logger.info("entrou em create compra");
		logger.info("compra recebida {}", compra);

		Grupo grupo = compra.getGrupo();
		logger.info("grupo: {}", grupo);

		grupo.getCompras().add(compra);
		compra.setGrupo(grupo);

		grupoService.salvar(grupo);

		String mensagem = String.format("A compra %s foi cadastrada com sucesso!", compra.getDescricao());
		redirectAttributes.addFlashAttribute("mensagem", mensagem);
		logger.trace("Redirecionando para a URL /mostrarmensagem");
		return new RedirectView("/mostrarmensagem");
	}
}
