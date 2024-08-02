package br.com.hiago640.estudo.novo_splitdumb.model.abstracts;

import br.com.hiago640.estudo.novo_splitdumb.model.Usuario;

public class Split {

	private Usuario usuario;
	double quantia;
	
	public Split(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getQuantia() {
		return quantia;
	}

	public void setQuantia(double quantia) {
		this.quantia = quantia;
	}

	@Override
	public String toString() {
		return String.format("Split [usuario=%s, quantia=%s]", usuario.getNomeUsu(), quantia);
	}

	
	
}
