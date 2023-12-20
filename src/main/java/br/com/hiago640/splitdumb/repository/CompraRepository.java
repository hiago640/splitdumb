package br.com.hiago640.splitdumb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.repository.queries.compra.CompraQueries;

public interface CompraRepository extends JpaRepository<Compra, UUID>, CompraQueries {

}
