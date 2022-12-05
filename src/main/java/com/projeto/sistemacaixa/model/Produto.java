package com.projeto.sistemacaixa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@Column(name = "preco", precision = 16, scale = 2)
	private BigDecimal preco;
	
	@Column(name = "descricao", length = 255)
	private String descricao;
	
	@Column(name = "data_cadastro", updatable = false)
	private LocalDate dataCadastro;
	
	@Column(name = "estoque")
	private Long estoque;

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Produto(String nome, BigDecimal preco, String descricao, Long estoque) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.estoque = estoque;
	}

	public Produto(Long id, String nome, BigDecimal preco, String descricao, Long estoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.estoque = estoque;
	}

	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Long getEstoque() {
		return estoque;
	}

	public void setEstoque(Long estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", estoque=" + estoque + ", descricao=" + descricao + "]";
	}
}
