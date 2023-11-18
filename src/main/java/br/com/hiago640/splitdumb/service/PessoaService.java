package br.com.hiago640.splitdumb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

	private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public void salvar(Pessoa pessoa) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método salvar");
		logger.trace(">>>>>>>>>>>>>>>> salvando pessoa: {}", pessoa);

		pessoaRepository.save(pessoa);

		logger.trace(">>>>>>>>>>>>>>>> pessoa salva!");

	}

	@Transactional
	public void alterar(Pessoa pessoa) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método alterar");
		logger.trace(">>>>>>>>>>>>>>>> salvando compra: {}", pessoa);

		pessoaRepository.save(pessoa);

		logger.trace(">>>>>>>>>>>>>>>> Pessoa alterada!");

	}

	@Transactional
	public void remover(Long id) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método remover");
		logger.trace(">>>>>>>>>>>>>>>> removendo a pessoa com o id: {}", id);

		pessoaRepository.deleteById(id);

		logger.trace(">>>>>>>>>>>>>>>> Pessoa removida");
	}
}
