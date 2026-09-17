package com.perfumaria.controleestoque.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
        @NotBlank(message = "O nome da categoria e obrigatorio")
        String nome
) {
}