package com.projeto.sistemacaixa.rest.produtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.sistemacaixa.model.Cliente;
import com.projeto.sistemacaixa.model.Produto;
import com.projeto.sistemacaixa.rest.clientes.ClienteFormRequest;

public class ProdutoFormRequest {
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	private String descricao;
	private Long estoque;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cadastro;
	
	
	public ProdutoFormRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProdutoFormRequest(Long id, String nome, BigDecimal preco, String descricao, Long estoque, LocalDate cadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.estoque = estoque;
		this.cadastro = cadastro;
	}

	public Produto toModel() {
		return new Produto(id, nome, preco, descricao, estoque);
	}
	
	public static ProdutoFormRequest fromModel(Produto produto) {
		return new ProdutoFormRequest(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getEstoque(), produto.getDataCadastro() );
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEstoque() {
		return estoque;
	}
	public void setEstoque(Long estoque) {
		this.estoque = estoque;
	}
	
	
	public LocalDate getCadastro() {
		return cadastro;
	}

	public void setCadastro(LocalDate cadastro) {
		this.cadastro = cadastro;
	}

}
	
	

