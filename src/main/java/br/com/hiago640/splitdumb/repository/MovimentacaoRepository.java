package br.com.hiago640.splitdumb.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.model.Movimentacao;
import br.com.hiago640.splitdumb.model.Pessoa;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, UUID> {

	public List<Movimentacao> findByCompra(Compra compra);
	
	public List<Movimentacao> findByCompraAndPessoa(Compra compra, Pessoa pessoa);
	
}
