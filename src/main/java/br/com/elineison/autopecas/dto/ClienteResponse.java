package br.com.elineison.autopecas.dto;

import br.com.elineison.autopecas.model.Cliente;

public record ClienteResponse(
        Long id,
        String nome,
        String documento,
        String telefone,
        String email
) {
    public ClienteResponse(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getDocumento(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }
}
