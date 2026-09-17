package com.perfumaria.controleestoque.controller;

import com.perfumaria.controleestoque.dto.ProdutoDTO;
import com.perfumaria.controleestoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.perfumaria.controleestoque.dto.ProdutoRespostaDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoRespostaDTO> cadastrar(
            @Valid @RequestBody ProdutoDTO dto) {

        ProdutoRespostaDTO produto = produtoService.cadastrar(dto);
        URI localizacao = URI.create("/produtos/" + produto.id());

        return ResponseEntity.created(localizacao).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoRespostaDTO>> listar() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoRespostaDTO>> buscarPorNome(
            @RequestParam String nome) {
        return ResponseEntity.ok(produtoService.buscarPorNome(nome));
    }
}
