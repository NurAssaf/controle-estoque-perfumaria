package com.perfumaria.controleestoque.controller;

import com.perfumaria.controleestoque.dto.CategoriaDTO;
import com.perfumaria.controleestoque.dto.CategoriaRespostaDTO;
import com.perfumaria.controleestoque.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Cadastro e consulta de categorias de produtos")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @Operation(summary = "Cadastra uma categoria")
    @PostMapping
    public ResponseEntity<CategoriaRespostaDTO> cadastrar(
            @Valid @RequestBody CategoriaDTO dto) {

        CategoriaRespostaDTO categoria = categoriaService.cadastrar(dto);
        URI localizacao = URI.create("/categorias/" + categoria.id());

        return ResponseEntity.created(localizacao).body(categoria);
    }
    @Operation(summary = "Lista todas as categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaRespostaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }
    @Operation(summary = "Busca uma categoria pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRespostaDTO> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }
}