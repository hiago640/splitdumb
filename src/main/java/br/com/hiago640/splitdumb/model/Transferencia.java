package br.com.hiago640.splitdumb.model;

import java.math.BigDecimal;

public class Transferencia {

	private Pessoa pagador;
	private BigDecimal valor;
	private TipoOperacaoEnum tipoOperacao;
	private Pessoa recebedor;
	
	public Transferencia() {
		
	}
	
	public Transferencia(Pessoa pagador) {
		this.pagador = pagador;
	}

	public Pessoa getPagador() {
		return pagador;
	}

	public void setPagador(Pessoa pagador) {
		this.pagador = pagador;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public BigDecimal getValorCalculado() {
		return valor.multiply(tipoOperacao.getValue());
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoOperacaoEnum getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Pessoa getRecebedor() {
		return recebedor;
	}

	public void setRecebedor(Pessoa recebedor) {
		this.recebedor = recebedor;
	}

	public void calcular() {
		
	}

}
