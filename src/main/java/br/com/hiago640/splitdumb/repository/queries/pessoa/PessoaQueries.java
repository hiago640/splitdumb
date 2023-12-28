package br.com.hiago640.splitdumb.repository.queries.pessoa;

import java.util.List;
import java.util.UUID;

import br.com.hiago640.splitdumb.model.Pessoa;

public interface PessoaQueries {

	public List<Pessoa> buscarComCompras();

	public Pessoa buscarComComprasByID(Long id);
	
	public Pessoa buscarComGruposByID(UUID id);
	
}
