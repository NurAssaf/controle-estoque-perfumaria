package com.perfumaria.controleestoque.exception;

public class FornecedorNaoEncontradoException extends RuntimeException {

    public FornecedorNaoEncontradoException(Long id) {
        super("Fornecedor nao encontrado com o id: " + id);
    }
}