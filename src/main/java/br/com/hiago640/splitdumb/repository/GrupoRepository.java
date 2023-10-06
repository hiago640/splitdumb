package br.com.hiago640.splitdumb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.queries.grupo.GrupoQueries;

public interface GrupoRepository extends JpaRepository<Grupo, Long>, GrupoQueries {

}
