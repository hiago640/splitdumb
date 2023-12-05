package br.com.hiago640.splitdumb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.repository.CompraRepository;
import jakarta.transaction.Transactional;

@Service
public class CompraService {

	private static final Logger logger = LoggerFactory.getLogger(CompraService.class);

	@Autowired
	private CompraRepository compraRepository;

	@Transactional
	public void salvar(Compra compra) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método salvar");
		logger.trace(">>>>>>>>>>>>>>>> salvando compra: {}", compra);
		
		compraRepository.save(compra);
		
		logger.trace(">>>>>>>>>>>>>>>> Compra salva!");

	}

	@Transactional
	public void alterar(Compra compra) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método alterar");
		logger.trace(">>>>>>>>>>>>>>>> salvando compra: {}", compra);
		
		compraRepository.save(compra);
		
		logger.trace(">>>>>>>>>>>>>>>> Compra alterada!");

	}

	@Transactional
	public void remover(Long id) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método remover");
		logger.trace(">>>>>>>>>>>>>>>> removendo a compra com o id: {}", id);
		
		compraRepository.deleteById(id);
		
		logger.trace(">>>>>>>>>>>>>>>> Compra removida");
	}
}