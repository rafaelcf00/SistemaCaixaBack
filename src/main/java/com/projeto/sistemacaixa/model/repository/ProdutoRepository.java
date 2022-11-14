package com.projeto.sistemacaixa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistemacaixa.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
