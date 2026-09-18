package com.perfumaria.controleestoque.controller;

import com.perfumaria.controleestoque.dto.FornecedorDTO;
import com.perfumaria.controleestoque.dto.FornecedorRespostaDTO;
import com.perfumaria.controleestoque.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fornecedores")
@Tag(name = "Fornecedores", description = "Cadastro e consulta de fornecedores com integração à ViaCEP")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }
    @Operation(summary = "Cadastra um fornecedor e consulta o endereço na ViaCEP")
    @PostMapping
    public ResponseEntity<FornecedorRespostaDTO> cadastrar(
            @Valid @RequestBody FornecedorDTO dto) {

        FornecedorRespostaDTO resposta = fornecedorService.cadastrar(dto);
        URI localizacao = URI.create("/fornecedores/" + resposta.id());

        return ResponseEntity.created(localizacao).body(resposta);
    }
    @Operation(summary = "Lista todos os fornecedores")
    @GetMapping
    public ResponseEntity<List<FornecedorRespostaDTO>> listarTodos() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }
    @Operation(summary = "Busca um fornecedor pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorRespostaDTO> buscarPorId(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(fornecedorService.buscarPorId(id));
    }
}