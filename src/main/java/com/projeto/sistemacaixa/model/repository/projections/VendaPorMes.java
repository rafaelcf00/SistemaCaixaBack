package com.projeto.sistemacaixa.model.repository.projections;

import java.math.BigDecimal;

public interface VendaPorMes {
	
	Integer getMes();
	BigDecimal getValor();

}
