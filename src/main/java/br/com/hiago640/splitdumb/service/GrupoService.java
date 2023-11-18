package br.com.hiago640.splitdumb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.GrupoRepository;
import jakarta.transaction.Transactional;

@Service
public class GrupoService {

	private static final Logger logger = LoggerFactory.getLogger(GrupoService.class);

	@Autowired
	private GrupoRepository grupoRepository;

	@Transactional
	public void salvar(Grupo grupo) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método salvar");
		logger.trace(">>>>>>>>>>>>>>>> salvando grupo: {}", grupo);

		grupoRepository.save(grupo);

		logger.trace(">>>>>>>>>>>>>>>> Grupo salvo!");
	}

	@Transactional
	public void alterar(Grupo grupo) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método alterar");
		logger.trace(">>>>>>>>>>>>>>>> salvando grupo: {}", grupo);

		grupoRepository.save(grupo);

		logger.trace(">>>>>>>>>>>>>>>> Grupo alterado!");
	}

	@Transactional
	public void remover(Long id) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método remover");
		logger.trace(">>>>>>>>>>>>>>>> removendo a grupo com o id: {}", id);

		grupoRepository.deleteById(id);

		logger.trace(">>>>>>>>>>>>>>>> Grupo removido");
	}
}
