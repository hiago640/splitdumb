package br.com.hiago640.estudo.novo_splitdumb.model.abstracts;

import java.util.List;

import br.com.hiago640.estudo.novo_splitdumb.model.DespesaInfo;
import br.com.hiago640.estudo.novo_splitdumb.model.Usuario;

public class Despesa {

	private String uuid;
	private Usuario comprador;
	private double vlrCompra;
	private List<Split> divisoes;
	private DespesaInfo informacoes;

	public Despesa(Usuario comprador, double vlrCompra, List<Split> divisoes,
			DespesaInfo informacoes) {
		this.comprador = comprador;
		this.vlrCompra = vlrCompra;
		this.divisoes = divisoes;
		this.informacoes = informacoes;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public double getVlrCompra() {
		return vlrCompra;
	}

	public void setVlrCompra(double vlrCompra) {
		this.vlrCompra = vlrCompra;
	}

	public List<Split> getDivisoes() {
		return divisoes;
	}

	public void setDivisoes(List<Split> divisoes) {
		this.divisoes = divisoes;
	}

	public DespesaInfo getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(DespesaInfo informacoes) {
		this.informacoes = informacoes;
	}

	@Override
	public String toString() {
		return String.format("Despesa [uuid=%s, comprador=%s, vlrCompra=%s, divisoes=%s]", uuid, comprador, vlrCompra,
				divisoes);
	}

}
