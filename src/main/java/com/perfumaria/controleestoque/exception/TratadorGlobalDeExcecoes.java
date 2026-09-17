package com.perfumaria.controleestoque.exception;

import com.perfumaria.controleestoque.dto.ErroResposta;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorGlobalDeExcecoes {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarProdutoNaoEncontrado(
            ProdutoNaoEncontradoException excecao,
            HttpServletRequest requisicao) {
        ErroResposta resposta = criarResposta(
                HttpStatus.NOT_FOUND,
                excecao.getMessage(),
                requisicao.getRequestURI(),
                Map.of());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarValidacao(
            MethodArgumentNotValidException excecao,
            HttpServletRequest requisicao) {
        Map<String, String> campos = new LinkedHashMap<>();
        excecao.getBindingResult().getFieldErrors()
                .forEach(erro -> campos.put(erro.getField(), erro.getDefaultMessage()));

        ErroResposta resposta = criarResposta(
                HttpStatus.BAD_REQUEST,
                "Existem campos invalidos na requisicao",
                requisicao.getRequestURI(),
                campos);
        return ResponseEntity.badRequest().body(resposta);
    }
    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<ErroResposta> tratarCategoriaNaoEncontrada(
            CategoriaNaoEncontradaException excecao,
            HttpServletRequest requisicao) {

        ErroResposta resposta = criarResposta(
                HttpStatus.NOT_FOUND,
                excecao.getMessage(),
                requisicao.getRequestURI(),
                Map.of());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    private ErroResposta criarResposta(
            HttpStatus status,
            String mensagem,
            String caminho,
            Map<String, String> campos) {
        return new ErroResposta(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                mensagem,
                caminho,
                campos);
    }
}
