package br.com.hiago640.splitdumb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.GrupoRepository;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private GrupoRepository grupoRepository;

	@GetMapping(value = { "/", "/index.html" })
	public ModelAndView index() {
		logger.trace(">>>>>>>>>>>>>>>> Entrou em index");
		logger.trace(">>>>>>>>>>>>>>>> Encaminhando para a view index");
		
        List<Grupo> grupos = grupoRepository.findAll();
        // logger.info("grupos encontrados: {}", grupos);


        ModelAndView model = new ModelAndView("index");
        model.addObject("grupos", grupos);
        
        return model;
	}

}
