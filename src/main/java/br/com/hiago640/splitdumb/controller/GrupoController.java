package br.com.hiago640.splitdumb.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import br.com.hiago640.splitdumb.repository.PessoaRepository;
import br.com.hiago640.splitdumb.service.GrupoService;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GrupoController.class);

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private GrupoService grupoService;

	@GetMapping("/new")
	public String abreCadastro(Grupo grupo) {
		LOGGER.trace("Entrou em abreCadastro");
		LOGGER.debug("encaminhando para view grupo/newgroup");

		return "grupo/newgroup";
	}

	@PostMapping("/new")
	public String cadastrarGrupo(Grupo grupo, RedirectAttributes redirectAttributes) {
		LOGGER.trace("Entrou em cadastrarGrupo");
		LOGGER.debug("Grupo recebida: {}", grupo);

		grupoService.salvar(grupo);
		redirectAttributes.addFlashAttribute("mensagem", "Grupo cadastrado com sucesso!");

		return "redirect:/mostrarmensagem";
	}

	@GetMapping("/list")
	public ModelAndView mostrarTodosGrupos() {
		LOGGER.trace("Entrou em mostrarTodosGrupo");

		List<Grupo> grupos = grupoRepository.buscarComParticipantes();

		LOGGER.debug("encaminhando para view grupo/listall");
		ModelAndView mv = new ModelAndView("grupo/listall");
		mv.addObject("grupos", grupos);

		return mv;
	}

	@GetMapping("/join")
	public ModelAndView entrarGrupo(Grupo grupo) {
		LOGGER.trace("Entrou em entrarGrupo");

		List<Grupo> grupos = grupoRepository.findAll();
		List<Pessoa> pessoas = pessoaRepository.findAll();

		LOGGER.debug("encaminhando para view grupo/join");
		ModelAndView mv = new ModelAndView("grupo/join");
		mv.addObject("grupos", grupos);
		mv.addObject("pessoas", pessoas);

		return mv;
	}

	@PostMapping("/join")
	public String joinGroup(String grupo, Pessoa pessoa, RedirectAttributes redirectAttributes) {
		LOGGER.trace("Entrou em joinGroup");
		LOGGER.debug("Grupo Encontrado: {}", grupo);
		LOGGER.debug("Pessoa Encontrada: {}", pessoa);

		Grupo g = grupoRepository.buscarComParticipantesByID(UUID.fromString(grupo));
		String msg;
		if (g != null) {
			g.getParticipantes().add(pessoa);
			LOGGER.info("usuários {}", g.getParticipantes());

			grupoService.alterar(g);

			msg = "Pessoa entrou no grupo com sucesso!";
		} else {
			msg = "Não foi possível buscar um grupo pelo ID.";
		}

		redirectAttributes.addFlashAttribute("mensagem", msg);
		return "redirect:/mostrarmensagem";
	}

}
