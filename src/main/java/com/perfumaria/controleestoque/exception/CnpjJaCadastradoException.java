package com.perfumaria.controleestoque.exception;

public class CnpjJaCadastradoException extends RuntimeException {

    public CnpjJaCadastradoException(String cnpj) {
        super("Ja existe um fornecedor cadastrado com o CNPJ: " + cnpj);
    }
}