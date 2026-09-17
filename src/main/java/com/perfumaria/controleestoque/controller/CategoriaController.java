package com.perfumaria.controleestoque.controller;

import com.perfumaria.controleestoque.dto.CategoriaDTO;
import com.perfumaria.controleestoque.dto.CategoriaRespostaDTO;
import com.perfumaria.controleestoque.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaRespostaDTO> cadastrar(
            @Valid @RequestBody CategoriaDTO dto) {

        CategoriaRespostaDTO categoria = categoriaService.cadastrar(dto);
        URI localizacao = URI.create("/categorias/" + categoria.id());

        return ResponseEntity.created(localizacao).body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaRespostaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRespostaDTO> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }
}