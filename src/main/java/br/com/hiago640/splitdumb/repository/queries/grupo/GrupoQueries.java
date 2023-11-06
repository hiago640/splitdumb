package br.com.hiago640.splitdumb.repository.queries.grupo;

import java.util.List;

import br.com.hiago640.splitdumb.model.Grupo;

public interface GrupoQueries {

	public List<Grupo> buscarComCompras();

	public Grupo buscarComComprasByID(Long id);

}
