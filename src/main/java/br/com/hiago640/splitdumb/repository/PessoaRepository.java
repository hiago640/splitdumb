package br.com.hiago640.splitdumb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
