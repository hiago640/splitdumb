package br.com.hiago640.splitdumb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.model.Pessoa;

public interface CompraRepository extends JpaRepository<Compra, Long> {

//	public List<Compra> findByCompradorContaining(Pessoa pessoa);
	
//	public Set<Compra> findByGrupoContaining(Grupo grupo);

}
