package com.perfumaria.controleestoque.service;

import com.perfumaria.controleestoque.client.ViaCepClient;
import com.perfumaria.controleestoque.dto.FornecedorDTO;
import com.perfumaria.controleestoque.dto.FornecedorRespostaDTO;
import com.perfumaria.controleestoque.dto.ViaCepRespostaDTO;
import com.perfumaria.controleestoque.entity.Fornecedor;
import com.perfumaria.controleestoque.exception.*;
import com.perfumaria.controleestoque.repository.FornecedorRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final ViaCepClient viaCepClient;

    public FornecedorService(
            FornecedorRepository fornecedorRepository,
            ViaCepClient viaCepClient) {
        this.fornecedorRepository = fornecedorRepository;
        this.viaCepClient = viaCepClient;
    }

    @Transactional
    public FornecedorRespostaDTO cadastrar(FornecedorDTO dto) {
        if (fornecedorRepository.existsByCnpj(dto.cnpj())) {
            throw new CnpjJaCadastradoException(dto.cnpj());
        }

        ViaCepRespostaDTO endereco = consultarEndereco(dto.cep());

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome().trim());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setCep(dto.cep());
        fornecedor.setNumero(dto.numero().trim());
        fornecedor.setRua(endereco.logradouro());
        fornecedor.setBairro(endereco.bairro());
        fornecedor.setCidade(endereco.localidade());
        fornecedor.setUf(endereco.uf());

        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return converterParaResposta(salvo);
    }

    @Transactional(readOnly = true)
    public List<FornecedorRespostaDTO> listarTodos() {
        return fornecedorRepository.findAll()
                .stream()
                .map(this::converterParaResposta)
                .toList();
    }

    @Transactional(readOnly = true)
    public FornecedorRespostaDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(
                        () -> new FornecedorNaoEncontradoException(id));

        return converterParaResposta(fornecedor);
    }

    private ViaCepRespostaDTO consultarEndereco(String cep) {
        ViaCepRespostaDTO endereco;

        try {
            endereco = viaCepClient.consultarCep(cep);
        } catch (FeignException excecao) {
            throw new ConsultaCepIndisponivelException(excecao);
        }

        if (endereco == null) {
            throw new ConsultaCepIndisponivelException(
                    new IllegalStateException("Resposta vazia da ViaCEP"));
        }

        if (Boolean.TRUE.equals(endereco.erro())) {
            throw new CepNaoEncontradoException(cep);
        }

        return endereco;
    }

    private FornecedorRespostaDTO converterParaResposta(
            Fornecedor fornecedor) {
        return new FornecedorRespostaDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                fornecedor.getCep(),
                fornecedor.getRua(),
                fornecedor.getNumero(),
                fornecedor.getBairro(),
                fornecedor.getCidade(),
                fornecedor.getUf()
        );
    }
}