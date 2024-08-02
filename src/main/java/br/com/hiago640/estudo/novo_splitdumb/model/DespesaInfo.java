package br.com.hiago640.estudo.novo_splitdumb.model;

public class DespesaInfo {

	private String nome;
	private String urlImg;
	private String informacoes;

	public DespesaInfo(String nome, String urlImg, String informacoes) {
		this.nome = nome;
		this.urlImg = urlImg;
		this.informacoes = informacoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

}
