package br.com.hiago640.splitdumb.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.CompraRepository;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import br.com.hiago640.splitdumb.service.CompraService;

@Controller
@RequestMapping("/compra")
public class CompraController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompraController.class);

	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	CompraRepository compraRepository;

	@Autowired
	CompraService compraService;

	@GetMapping("/choose-group")
	public ModelAndView abrirEscolherGrupo() {
		LOGGER.trace("Entrou em abrirEscolherGrupo");

		List<Grupo> grupos = grupoRepository.findAll();

		LOGGER.trace("Encaminhando para view compra/choosegroup");
		ModelAndView modelAndView = new ModelAndView("compra/choosegroup");
		modelAndView.addObject("grupos", grupos);

		return modelAndView;
	}

	@PostMapping("/choose-group")
	public ModelAndView abrirCadastroCompra(String grupo, HttpSession session) {
		LOGGER.trace("Entrou em abrirCadastroCompra");

		LOGGER.debug("Grupo recebido: {}", grupo);
		Grupo group = grupoRepository.buscarComParticipantesByID(UUID.fromString(grupo));
		session.setAttribute("grupo", group);

		ModelAndView modelAndView = new ModelAndView("compra/newpurchase");
		modelAndView.addObject("grupo", group);
		return modelAndView;
	}

	@PostMapping("/new")
	public ModelAndView novaCompra(Compra compra, HttpSession session, RedirectAttributes attributes) {
		LOGGER.trace("Entrou em abrirCadastroCompra");

		LOGGER.debug("compra recebida: {}", compra);
		LOGGER.debug("Comprador recebido: {}", compra.getComprador());
		LOGGER.debug("Envolvidos na compra: {}", compra.getEnvolvidos());

		Grupo grupo = (Grupo) session.getAttribute("grupo");
		compra.setGrupo(grupo);

		if (compra.getEnvolvidos().isEmpty())
			compra.getEnvolvidos().add(compra.getComprador());

		compraService.salvar(compra);

		session.removeAttribute("grupo");
		attributes.addFlashAttribute("mensagem", "Compra cadastrada com sucesso");

		Grupo buscarComComprasByID = grupoRepository.buscarComComprasByID(grupo.getCodigo());
		LOGGER.debug("buscarComComprasByID recebida: {}", buscarComComprasByID.getCompras());

		LOGGER.trace("Redirecionando para a view /mostrarmensagem");
		return new ModelAndView("redirect:/mostrarmensagem");
	}

	@GetMapping("/list")
	public ModelAndView mostrarTodasCompras() {
		LOGGER.trace("Entrou em mostrarTodasCompras");

		List<Compra> compras = compraRepository.buscarEnvolvidos();

		LOGGER.debug("encaminhando para view compra/listall");
		ModelAndView mv = new ModelAndView("compra/listall");
		mv.addObject("compras", compras);

		return mv;
	}

}
