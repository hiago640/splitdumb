package br.com.hiago640.splitdumb.repository.queries.compra;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.model.CompraDTO;
import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.MovimentacaoRepository;
import br.com.hiago640.splitdumb.repository.PessoaRepository;

public class CompraQueriesImpl implements CompraQueries {

	private static final Logger logger = LoggerFactory.getLogger(CompraQueriesImpl.class);

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	MovimentacaoRepository movimentacaoRepository;

	@Override
	public List<Compra> buscarEnvolvidos() {
		logger.info("Entrou no método buscarEnvolvidos");

		String jpql = "SELECT distinct c FROM Compra c LEFT JOIN FETCH c.envolvidos envolvidos";
		TypedQuery<Compra> query = manager.createQuery(jpql, Compra.class);

		List<Compra> compras = query.getResultList();
		logger.info("compras encontrado no BD: {}", compras);

		return compras;
	}
	
	@Override
	public List<Compra> buscarEnvolvidosByGrupo(Grupo grupo) {
		logger.info("Entrou no método buscarEnvolvidos");

		String jpql = "SELECT distinct c FROM Compra c LEFT JOIN FETCH c.envolvidos envolvidos where c.grupo.id = :id";
		TypedQuery<Compra> query = manager.createQuery(jpql, Compra.class);
		query.setParameter("id", grupo.getCodigo());

		List<Compra> compras = query.getResultList();
		logger.info("compras encontrado no BD: {}", compras);

		return compras;
	}

	@Override
	public List<Compra> buscarCompraComUsuarioEnvolvido(Pessoa pessoa) {
		logger.info("Entrou no método buscarCompraComUsuarioEnvolvido");
		logger.debug("Valor de ID recebido: {}", pessoa.getCodigo());

		String jpql = "SELECT distinct c FROM Compra c JOIN FETCH c.envolvidos envolvidos where envolvidos.codigo = :codigo";
		TypedQuery<Compra> query = manager.createQuery(jpql, Compra.class);
		query.setParameter("codigo", pessoa.getCodigo());

		List<Compra> compras = query.getResultList();
		logger.info("compras encontrado no BD: {}", compras);

		return compras;

	}

	@Override
	public List<CompraDTO> buscarComprasDoGrupo(Grupo grupo) {
		logger.info("Entrou no método buscarComprasDoGrupo");

		List<Compra> compras = (grupo == null) ? buscarEnvolvidos() : buscarEnvolvidosByGrupo(grupo);

        // Combinar os resultados em CompraDTOs
        List<CompraDTO> comprasDTO = new ArrayList<>();
        for (Compra compra : compras) {
            CompraDTO compraDTO = new CompraDTO();
            compraDTO.setCodigo(compra.getCodigo());
            compraDTO.setDescricao(compra.getDescricao());
            compraDTO.setValorCompra(compra.getValorCompra());
            compraDTO.setComprador(compra.getComprador());
            compraDTO.setGrupo(compra.getGrupo());
            compraDTO.setEnvolvidos(compra.getEnvolvidos());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		Pessoa pessoa = pessoaRepository.findByUsername(auth.getName());
    		
            compraDTO.setMovimentacoes(movimentacaoRepository.findByCompraAndPessoa(compra, pessoa));

            comprasDTO.add(compraDTO);
        }

        return comprasDTO;
    }


}
