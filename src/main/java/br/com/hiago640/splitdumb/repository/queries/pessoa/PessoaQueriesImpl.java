package br.com.hiago640.splitdumb.repository.queries.pessoa;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.hiago640.splitdumb.model.Pessoa;

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

	@Override
	public Pessoa buscarComGruposByID(UUID id) {
		logger.trace("Entrou no método buscarComGruposByID");
		logger.debug("Valor de ID recebido: {}", id);

		String jpql = "select p from Pessoa p LEFT join fetch p.grupos where p.id = :id";
		TypedQuery<Pessoa> query = manager.createQuery(jpql, Pessoa.class);
		query.setParameter("id", id);

		Pessoa pessoa = query.getSingleResult();
		logger.debug("Pessoa buscados no BD: {}", pessoa);

		return pessoa;
	}

}
