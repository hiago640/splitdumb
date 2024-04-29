package br.com.hiago640.splitdumb.model;

import java.math.BigDecimal;

public class TotalPorPessoaDTO {
	
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal tipoOperacao;

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(BigDecimal tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

}
