package com.perfumaria.controleestoque.controller;

import com.perfumaria.controleestoque.dto.FornecedorDTO;
import com.perfumaria.controleestoque.dto.FornecedorRespostaDTO;
import com.perfumaria.controleestoque.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<FornecedorRespostaDTO> cadastrar(
            @Valid @RequestBody FornecedorDTO dto) {

        FornecedorRespostaDTO resposta = fornecedorService.cadastrar(dto);
        URI localizacao = URI.create("/fornecedores/" + resposta.id());

        return ResponseEntity.created(localizacao).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorRespostaDTO>> listarTodos() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorRespostaDTO> buscarPorId(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(fornecedorService.buscarPorId(id));
    }
}