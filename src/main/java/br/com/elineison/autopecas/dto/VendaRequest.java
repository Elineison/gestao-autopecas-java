package br.com.elineison.autopecas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VendaRequest(
        @NotNull Long clienteId,
        @NotNull Long produtoId,
        @NotNull @Min(1) Integer quantidade
) {
}
