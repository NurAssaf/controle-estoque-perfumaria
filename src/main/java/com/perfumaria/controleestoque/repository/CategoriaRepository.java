package com.perfumaria.controleestoque.repository;

import com.perfumaria.controleestoque.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}