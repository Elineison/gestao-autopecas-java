package br.com.elineison.autopecas.dto;

import br.com.elineison.autopecas.model.Venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaResponse(
        Long id,
        String cliente,
        String produto,
        Integer quantidade,
        BigDecimal valorUnitario,
        BigDecimal valorTotal,
        LocalDateTime vendidaEm
) {
    public VendaResponse(Venda venda) {
        this(
                venda.getId(),
                venda.getCliente().getNome(),
                venda.getProduto().getNome(),
                venda.getQuantidade(),
                venda.getValorUnitario(),
                venda.getValorTotal(),
                venda.getVendidaEm()
        );
    }
}
