package com.perfumaria.controleestoque.dto;

public record ProdutoRespostaDTO(
        Long id,
        String nome,
        String marca,
        Double preco,
        Integer quantidadeEstoque,
        CategoriaRespostaDTO categoria
) {
}