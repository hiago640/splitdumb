package br.com.hiago640.splitdumb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, UUID> {

}
