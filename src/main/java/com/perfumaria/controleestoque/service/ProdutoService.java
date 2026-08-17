package com.perfumaria.controleestoque.service;

import com.perfumaria.controleestoque.dto.ProdutoDTO;
import com.perfumaria.controleestoque.entity.Produto;
import com.perfumaria.controleestoque.exception.ProdutoNaoEncontradoException;
import com.perfumaria.controleestoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(ProdutoDTO dto) {
        Produto produto = new Produto();
        copiarDados(dto, produto);
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto produto = buscarPorId(id);
        copiarDados(dto, produto);
        return produtoRepository.save(produto);
    }

    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    private void copiarDados(ProdutoDTO dto, Produto produto) {
        produto.setNome(dto.nome().trim());
        produto.setMarca(dto.marca().trim());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());
    }
}
