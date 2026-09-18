package com.perfumaria.controleestoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoDTO(
        @NotBlank(message = "O nome e obrigatorio")
        String nome,

        @NotBlank(message = "A marca e obrigatoria")
        String marca,

        @NotNull(message = "O preco e obrigatorio")
        @Positive(message = "O preco deve ser maior que zero")
        Double preco,

        @NotNull(message = "A quantidade em estoque e obrigatoria")
        @PositiveOrZero(message = "A quantidade em estoque nao pode ser negativa")
        Integer quantidadeEstoque,

        @Positive(message = "O ID da categoria deve ser positivo")
        Long categoriaId,

        @Positive(message = "O ID do fornecedor deve ser positivo")
        Long fornecedorId
) {
}