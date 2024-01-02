package br.com.hiago640.splitdumb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, UUID>{

}
