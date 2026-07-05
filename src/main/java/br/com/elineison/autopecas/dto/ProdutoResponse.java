package br.com.elineison.autopecas.dto;

import br.com.elineison.autopecas.model.CategoriaProduto;
import br.com.elineison.autopecas.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String codigo,
        CategoriaProduto categoria,
        BigDecimal preco,
        Integer quantidadeEstoque,
        Integer estoqueMinimo,
        boolean precisaReposicao
) {
    public ProdutoResponse(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getCodigo(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getQuantidadeEstoque(),
                produto.getEstoqueMinimo(),
                produto.precisaReposicao()
        );
    }
}
