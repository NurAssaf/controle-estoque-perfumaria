package com.perfumaria.controleestoque.dto;

public record FornecedorRespostaDTO(
        Long id,
        String nome,
        String cnpj,
        String cep,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String uf
) {
}