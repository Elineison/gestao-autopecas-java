package br.com.elineison.autopecas.repository;

import br.com.elineison.autopecas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
