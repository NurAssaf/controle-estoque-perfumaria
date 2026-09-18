package com.perfumaria.controleestoque.exception;

public class ConsultaCepIndisponivelException extends RuntimeException {

    public ConsultaCepIndisponivelException(Throwable causa) {
        super("Nao foi possivel consultar o CEP. Tente novamente mais tarde.",
                causa);
    }
}