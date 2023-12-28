package br.com.hiago640.splitdumb.model;

import java.math.BigDecimal;

public enum TipoOperacaoEnum {

	RECEITA(BigDecimal.ONE),
	DESPESA(BigDecimal.ONE.negate());

	private BigDecimal value;
	
	private TipoOperacaoEnum(BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
}
