package com.projeto.sistemacaixa.rest.dashboard;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.sistemacaixa.model.repository.ClienteRepository;
import com.projeto.sistemacaixa.model.repository.ProdutoRepository;
import com.projeto.sistemacaixa.model.repository.VendaRepository;
import com.projeto.sistemacaixa.model.repository.projections.VendaPorMes;

@RestController
@RequestMapping("/api/dashboard")

public class DashboardController {
	
	@Autowired
	private VendaRepository vendas;
	@Autowired
	private ClienteRepository clientes;
	@Autowired
	private ProdutoRepository produtos;
	
	@GetMapping
	public DashboardData getDashBoard() {
		Long vendasCount = vendas.count();
		Long clientesCount = clientes.count();
		Long produtosCount = produtos.count();
		
		int anoCorrente = LocalDate.now().getYear();
		List<VendaPorMes> vendasPorMes = vendas.obterSomatoriaVendasPorMes(anoCorrente);
		
		return new DashboardData(produtosCount, clientesCount, vendasCount, vendasPorMes);
	}
	
}