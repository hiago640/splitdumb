package br.com.hiago640.splitdumb.repository.queries.pessoa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.queries.pessoa.PessoaQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class PessoaQueriesImpl implements PessoaQueries {

	private static final Logger logger = LoggerFactory.getLogger(PessoaQueriesImpl.class);

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Pessoa buscarComComprasByID(Long id) {
		logger.trace("Entrou no método buscarComComprasByID");
		logger.debug("Valor de ID recebido: {}", id);

		String jpql = "select p from Pessoa p LEFT join fetch p.compras where p.id = :id";
		TypedQuery<Pessoa> query = manager.createQuery(jpql, Pessoa.class);
		query.setParameter("id", id);

		Pessoa pessoa = query.getSingleResult();
		logger.debug("Pessoa buscados no BD: {}", pessoa);

		return pessoa;
	}

	@Override
	public List<Pessoa> buscarComCompras() {
		logger.info("Entrou no método buscarComCompras");

		String jpql = "SELECT p FROM Pessoa p LEFT JOIN FETCH p.compras compras";
		TypedQuery<Pessoa> query = manager.createQuery(jpql, Pessoa.class);

		List<Pessoa> pessoas = query.getResultList();
		logger.info("Pessoas buscados no BD: {}", pessoas);

		return pessoas;
	}

}
