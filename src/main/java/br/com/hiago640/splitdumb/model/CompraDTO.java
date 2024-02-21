package br.com.hiago640.splitdumb.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CompraDTO {

	private UUID codigo;
	private String descricao;
	private BigDecimal valorCompra;
	private Pessoa comprador;
	private Grupo grupo;
	private List<Pessoa> envolvidos;
	private List<Movimentacao> movimentacoes;

	public CompraDTO() {

	}

	public CompraDTO(UUID codigo, String descricao, BigDecimal valorCompra, Pessoa comprador, Grupo grupo,
			List<Movimentacao> movimentacoes) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valorCompra = valorCompra;
		this.comprador = comprador;
		this.grupo = grupo;
		this.movimentacoes = movimentacoes;
	}

	public UUID getCodigo() {
		return codigo;
	}

	public void setCodigo(UUID codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Pessoa getComprador() {
		return comprador;
	}

	public void setComprador(Pessoa comprador) {
		this.comprador = comprador;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public List<Pessoa> getEnvolvidos() {
		return envolvidos;
	}

	public void setEnvolvidos(List<Pessoa> envolvidos) {
		this.envolvidos = envolvidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CompraDTO))
			return false;
		CompraDTO other = (CompraDTO) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return String.format(
				"CompraDTO [codigo=%s, descricao=%s, valorCompra=%s, comprador=%s, grupo=%s, movimentacoes=%s]", codigo,
				descricao, valorCompra, comprador, grupo, movimentacoes);
	}

}
