package br.com.hiago640.splitdumb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public List<Pessoa> findByGruposContaining(Grupo grupo);

}
