package com.projeto.sistemacaixa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistemacaixa.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
