package com.projeto.sistemacaixa.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.projeto.sistemacaixa.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query(" select p from Produto p where upper(p.nome) like upper(:nome) ")
	Page<Produto> buscarPorNome(
			@Param("nome") String nome,
			Pageable pageable);
}
