package br.com.hiago640.splitdumb.repository.queries.grupo;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.hiago640.splitdumb.model.Grupo;

public class GrupoQueriesImpl implements GrupoQueries {

	private static final Logger logger = LoggerFactory.getLogger(GrupoQueriesImpl.class);

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Grupo buscarComComprasByID(UUID id) {
		logger.trace("Entrou no método buscarComComprasByID");
		logger.debug("Valor de ID recebido: {}", id);

		String jpql = "select g from Grupo g LEFT join fetch g.compras where g.id = :id";
		TypedQuery<Grupo> query = manager.createQuery(jpql, Grupo.class);
		query.setParameter("id", id);

		Grupo grupo = query.getSingleResult();
		logger.debug("Grupo buscados no BD: {}", grupo);

		return grupo;
	}

	@Override
	public List<Grupo> buscarComCompras() {
		logger.info("Entrou no método buscarComCompras");

		String jpql = "SELECT g FROM Grupo g LEFT JOIN FETCH g.compras compras";
		TypedQuery<Grupo> query = manager.createQuery(jpql, Grupo.class);

		List<Grupo> grupos = query.getResultList();
		logger.info("Grupos buscados no BD: {}", grupos);

		return grupos;
	}

	@Override
	public List<Grupo> buscarComParticipantes() {
		logger.info("Entrou no método buscarComParticipantes");

		String jpql = "SELECT distinct g FROM Grupo g LEFT JOIN FETCH g.participantes";
		TypedQuery<Grupo> query = manager.createQuery(jpql, Grupo.class);
		List<Grupo> grupos = query.getResultList();
		logger.info("Grupos buscados no BD: {}", grupos);

		return grupos;
	}

	@Override
	public Grupo buscarComParticipantesByID(UUID id) {
		logger.info("Entrou no método buscarComParticipantesByID");
		logger.debug("Id recebido: {}", id);

		String jpql = "SELECT distinct g FROM Grupo g LEFT JOIN FETCH g.participantes participantes where g.id = :id";
		TypedQuery<Grupo> query = manager.createQuery(jpql, Grupo.class);
		query.setParameter("id", id);
		
		Grupo grupo = query.getSingleResult();
		logger.info("Grupo encontrado no BD: {}", grupo);

		return grupo;
		
	}

	@Override
	public Grupo buscarComParticipantesECompraByID(UUID id) {
		logger.info("Entrou no método buscarComParticipantesECompraByID");
		logger.debug("Id recebido: {}", id);

		String jpql = "SELECT distinct g FROM Grupo g LEFT JOIN FETCH g.participantes participantes LEFT JOIN FETCH g.compras compras where g.id = :id";
		TypedQuery<Grupo> query = manager.createQuery(jpql, Grupo.class);
		query.setParameter("id", id);
		
		Grupo grupo = query.getSingleResult();
		logger.info("Grupo encontrado no BD: {}", grupo);

		return grupo;
	}

}
