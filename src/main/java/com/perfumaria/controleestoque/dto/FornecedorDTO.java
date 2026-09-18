package com.perfumaria.controleestoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FornecedorDTO(

        @NotBlank(message = "O nome e obrigatorio")
        @Size(max = 255, message = "O nome deve ter ate 255 caracteres")
        String nome,

        @NotBlank(message = "O CNPJ e obrigatorio")
        @Pattern(
                regexp = "[0-9]{14}",
                message = "O CNPJ deve conter 14 digitos, sem pontuacao"
        )
        String cnpj,

        @NotBlank(message = "O CEP e obrigatorio")
        @Pattern(
                regexp = "[0-9]{8}",
                message = "O CEP deve conter 8 digitos, sem pontuacao"
        )
        String cep,

        @NotBlank(message = "O numero e obrigatorio")
        @Size(max = 255, message = "O numero deve ter ate 255 caracteres")
        String numero
) {
}