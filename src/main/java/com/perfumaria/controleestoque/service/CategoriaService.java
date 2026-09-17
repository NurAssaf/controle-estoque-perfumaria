package com.perfumaria.controleestoque.service;

import com.perfumaria.controleestoque.dto.CategoriaDTO;
import com.perfumaria.controleestoque.dto.CategoriaRespostaDTO;
import com.perfumaria.controleestoque.entity.Categoria;
import com.perfumaria.controleestoque.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.perfumaria.controleestoque.exception.CategoriaNaoEncontradaException;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaRespostaDTO cadastrar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome().trim());

        Categoria salva = categoriaRepository.save(categoria);
        return converterParaResposta(salva);
    }

    @Transactional(readOnly = true)
    public List<CategoriaRespostaDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::converterParaResposta)
                .toList();
    }

    private CategoriaRespostaDTO converterParaResposta(Categoria categoria) {
        return new CategoriaRespostaDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }

    @Transactional(readOnly = true)
    public CategoriaRespostaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));

        return converterParaResposta(categoria);
    }
}