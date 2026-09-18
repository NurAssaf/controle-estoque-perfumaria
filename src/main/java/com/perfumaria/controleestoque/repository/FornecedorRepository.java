package com.perfumaria.controleestoque.repository;

import com.perfumaria.controleestoque.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository
        extends JpaRepository<Fornecedor, Long> {

    boolean existsByCnpj(String cnpj);
}