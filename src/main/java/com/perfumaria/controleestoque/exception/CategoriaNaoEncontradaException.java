package com.perfumaria.controleestoque.exception;

public class CategoriaNaoEncontradaException extends RuntimeException {

    public CategoriaNaoEncontradaException(Long id) {
        super("Categoria nao encontrada com o id: " + id);
    }
}