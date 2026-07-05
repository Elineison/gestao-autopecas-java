package br.com.elineison.autopecas.dto;

import br.com.elineison.autopecas.model.CategoriaProduto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank String nome,
        @NotBlank String codigo,
        @NotNull CategoriaProduto categoria,
        @NotNull @DecimalMin("0.01") BigDecimal preco,
        @NotNull @Min(0) Integer quantidadeEstoque,
        @NotNull @Min(0) Integer estoqueMinimo
) {
}
