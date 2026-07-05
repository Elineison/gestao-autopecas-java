package br.com.elineison.autopecas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank String nome,
        @NotBlank String documento,
        @NotBlank String telefone,
        @Email @NotBlank String email
) {
}
