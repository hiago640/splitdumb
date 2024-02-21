package br.com.hiago640.splitdumb.repository.queries.compra;

import java.util.List;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.model.CompraDTO;
import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.model.Pessoa;

public interface CompraQueries {

	public List<Compra> buscarEnvolvidos();
	
	public List<Compra> buscarEnvolvidosByGrupo(Grupo grupo);
	
	public List<Compra> buscarCompraComUsuarioEnvolvido(Pessoa pessoa);
	
	public List<CompraDTO> buscarComprasDoGrupo(Grupo grupo);
	
}
