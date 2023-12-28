package br.com.hiago640.splitdumb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hiago640.splitdumb.model.Movimentacao;
import br.com.hiago640.splitdumb.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

	private static final Logger logger = LoggerFactory.getLogger(MovimentacaoService.class);

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Transactional
	public void salvar(Movimentacao movimentacao) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método salvar");
		logger.trace(">>>>>>>>>>>>>>>> salvando movimentação: {}", movimentacao);

		movimentacaoRepository.save(movimentacao);

		logger.trace(">>>>>>>>>>>>>>>> movimentacao salva!");

	}

}
