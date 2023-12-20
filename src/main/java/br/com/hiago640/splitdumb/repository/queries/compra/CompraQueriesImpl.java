package br.com.hiago640.splitdumb.repository.queries.compra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.hiago640.splitdumb.model.Compra;

public class CompraQueriesImpl implements CompraQueries {

	private static final Logger logger = LoggerFactory.getLogger(CompraQueriesImpl.class);

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Compra> buscarEnvolvidos() {
		logger.info("Entrou no método buscarEnvolvidos");

		String jpql = "SELECT distinct c FROM Compra c LEFT JOIN FETCH c.envolvidos envolvidos";
		TypedQuery<Compra> query = manager.createQuery(jpql, Compra.class);

		List<Compra> compras = query.getResultList();
		logger.info("compras encontrado no BD: {}", compras);

		return compras;
	}

}
