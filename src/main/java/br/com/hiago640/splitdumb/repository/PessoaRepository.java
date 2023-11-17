package br.com.hiago640.splitdumb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.repository.queries.pessoa.PessoaQueries;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaQueries {

	public List<Pessoa> findByGruposContaining(Grupo grupo);

}
