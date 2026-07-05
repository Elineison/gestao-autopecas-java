package br.com.elineison.autopecas.repository;

import br.com.elineison.autopecas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByCodigo(String codigo);

    List<Produto> findByQuantidadeEstoqueLessThanEqual(Integer quantidade);
}
