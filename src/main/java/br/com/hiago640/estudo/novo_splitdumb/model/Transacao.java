package br.com.hiago640.estudo.novo_splitdumb.model;

public class Transacao {
	private Usuario devedor;
	private Usuario credor;
	private double quantia;

	public Transacao(Usuario devedor, Usuario credor, double quantia) {
		this.devedor = devedor;
		this.credor = credor;
		this.quantia = quantia;
	}

	public Usuario getDevedor() {
		return devedor;
	}

	public void setDevedor(Usuario devedor) {
		this.devedor = devedor;
	}

	public Usuario getCredor() {
		return credor;
	}

	public void setCredor(Usuario credor) {
		this.credor = credor;
	}

	public double getQuantia() {
		return quantia;
	}

	public void setQuantia(double quantia) {
		this.quantia = quantia;
	}

}