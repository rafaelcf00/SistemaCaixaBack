package com.projeto.sistemacaixa.rest.produtos;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.sistemacaixa.model.Cliente;
import com.projeto.sistemacaixa.model.ItemVenda;
import com.projeto.sistemacaixa.model.Produto;
import com.projeto.sistemacaixa.model.Venda;
import com.projeto.sistemacaixa.model.repository.ItemVendaRepository;
import com.projeto.sistemacaixa.model.repository.ProdutoRepository;
import com.projeto.sistemacaixa.model.repository.VendaRepository;
import com.projeto.sistemacaixa.rest.clientes.ClienteFormRequest;
import com.projeto.sistemacaixa.service.exception.DataIntegrityViolationException;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ItemVendaRepository itemVendaRepository;
	

	@GetMapping("{id}")
	public ResponseEntity<ProdutoFormRequest> getById(@PathVariable Long id) {
		
		return repository.findById(id)
				.map(ProdutoFormRequest::fromModel)
				.map(produtoFR -> ResponseEntity.ok(produtoFR))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	
	@PostMapping
	public ResponseEntity salvar (@RequestBody ProdutoFormRequest request) {
		
		Produto produto = request.toModel();
		repository.save(produto);
		return ResponseEntity.ok(ProdutoFormRequest.fromModel(produto));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar (@PathVariable Long id, @RequestBody ProdutoFormRequest request) {
		
		Optional<Produto> produtoExistente = repository.findById(id);
		
		if (produtoExistente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Produto produto = request.toModel();
		produto.setId(id);
		repository.save(produto);
		return ResponseEntity.noContent().build();
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
			
			Produto objProduto = repository.findById(id).get();
		
			List<Long> listaVendas = (List<Long>) itemVendaRepository
					.findAll()
					.stream()
					.map(ItemVenda -> ItemVenda.getProduto()
							.getId()).collect(Collectors.toList());
			
//			List<ItemVenda> listaVendas = itemVendaRepository.findAll();
			
			for (int i = 0; i < listaVendas.size(); i++) {
				
				if (listaVendas.get(i) == objProduto.getId()) {
					
					throw new DataIntegrityViolationException("O produto possui vendas associados a ele.");
					
				}
				
			}
		
//		   if (obj.getProduto().getId() == objProduto.getId() ) {
//
//	            throw new DataIntegrityViolationException("O produto possui vendas associados a ele.");
//	        }
		
		return repository.findById(id)
				.map(produto -> {
					repository.delete(produto);
					return ResponseEntity.noContent().build();
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	@GetMapping
	public Page<ProdutoFormRequest> getLista( 
		
			@RequestParam(value = "nome", required = false, defaultValue = "")
			String nome,
			Pageable pageable ) {
		return repository
				.buscarPorNome("%" + nome + "%", pageable)
				.map(ProdutoFormRequest::fromModel);
				
	}
}
