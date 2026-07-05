package br.com.elineison.autopecas.repository;

import br.com.elineison.autopecas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
