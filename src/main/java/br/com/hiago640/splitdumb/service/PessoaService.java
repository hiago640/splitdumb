package br.com.hiago640.splitdumb.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.PessoaRepository;

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
		logger.trace(">>>>>>>>>>>>>>>> salvando pessoa: {}", pessoa);

		pessoaRepository.save(pessoa);

		logger.trace(">>>>>>>>>>>>>>>> Pessoa alterada!");

	}

	@Transactional
	public boolean ativarConta(UUID token) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método ativarConta");
		Pessoa pessoa = pessoaRepository.findByTokenConfirmacao(token);

		if (pessoa != null) {
			// Se o token for válido, ativa a conta
			pessoa.setAtivo(true);
			pessoa.setTokenConfirmacao(null);
			
			pessoaRepository.save(pessoa);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(pessoa, null, pessoa.getAuthorities());
    	    SecurityContextHolder.getContext().setAuthentication(authentication);
    	    
			return true; // Conta ativada com sucesso
		} else {
			return false; // Token de confirmação inválido
		}

	}
}
