package com.perfumaria.controleestoque.service;

import com.perfumaria.controleestoque.dto.ProdutoDTO;
import com.perfumaria.controleestoque.entity.Produto;
import com.perfumaria.controleestoque.exception.ProdutoNaoEncontradoException;
import com.perfumaria.controleestoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import com.perfumaria.controleestoque.repository.CategoriaRepository;
import com.perfumaria.controleestoque.exception.CategoriaNaoEncontradaException;
import com.perfumaria.controleestoque.dto.ProdutoRespostaDTO;
import com.perfumaria.controleestoque.dto.CategoriaRespostaDTO;
import org.springframework.transaction.annotation.Transactional;
import com.perfumaria.controleestoque.dto.FornecedorRespostaDTO;
import com.perfumaria.controleestoque.entity.Fornecedor;
import com.perfumaria.controleestoque.exception.FornecedorNaoEncontradoException;
import com.perfumaria.controleestoque.repository.FornecedorRepository;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final FornecedorRepository fornecedorRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            CategoriaRepository categoriaRepository,
            FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public ProdutoRespostaDTO cadastrar(ProdutoDTO dto) {
        Produto produto = new Produto();
        copiarDados(dto, produto);

        Produto salvo = produtoRepository.save(produto);
        return converterParaResposta(salvo);
    }

    @Transactional(readOnly = true)
    public List<ProdutoRespostaDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::converterParaResposta)
                .toList();
    }

    private Produto buscarEntidadePorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public ProdutoRespostaDTO buscarPorId(Long id) {
        return converterParaResposta(buscarEntidadePorId(id));
    }

    @Transactional
    public ProdutoRespostaDTO atualizar(Long id, ProdutoDTO dto) {
        Produto produto = buscarEntidadePorId(id);
        copiarDados(dto, produto);

        Produto salvo = produtoRepository.save(produto);
        return converterParaResposta(salvo);
    }

    @Transactional
    public void excluir(Long id) {
        Produto produto = buscarEntidadePorId(id);
        produtoRepository.delete(produto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoRespostaDTO> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::converterParaResposta)
                .toList();
    }

    private void copiarDados(ProdutoDTO dto, Produto produto) {
        produto.setNome(dto.nome().trim());
        produto.setMarca(dto.marca().trim());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());

        if (dto.categoriaId() != null) {
            produto.setCategoria(
                    categoriaRepository.findById(dto.categoriaId())
                            .orElseThrow(() ->
                                    new CategoriaNaoEncontradaException(
                                            dto.categoriaId()))
            );
        } else {
            produto.setCategoria(null);
        }

        if (dto.fornecedorId() != null) {
            produto.setFornecedor(
                    fornecedorRepository.findById(dto.fornecedorId())
                            .orElseThrow(() ->
                                    new FornecedorNaoEncontradoException(
                                            dto.fornecedorId()))
            );
        } else {
            produto.setFornecedor(null);
        }
    }

    private ProdutoRespostaDTO converterParaResposta(Produto produto) {
        CategoriaRespostaDTO categoria = null;

        if (produto.getCategoria() != null) {
            categoria = new CategoriaRespostaDTO(
                    produto.getCategoria().getId(),
                    produto.getCategoria().getNome()
            );
        }

        FornecedorRespostaDTO fornecedor = null;

        if (produto.getFornecedor() != null) {
            Fornecedor entidade = produto.getFornecedor();

            fornecedor = new FornecedorRespostaDTO(
                    entidade.getId(),
                    entidade.getNome(),
                    entidade.getCnpj(),
                    entidade.getCep(),
                    entidade.getRua(),
                    entidade.getNumero(),
                    entidade.getBairro(),
                    entidade.getCidade(),
                    entidade.getUf()
            );
        }

        return new ProdutoRespostaDTO(
                produto.getId(),
                produto.getNome(),
                produto.getMarca(),
                produto.getPreco(),
                produto.getQuantidadeEstoque(),
                categoria,
                fornecedor
        );
    }
}
