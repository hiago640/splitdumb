package br.com.hiago640.splitdumb.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {

	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char") // Esta linha configura a coluna como VARCHAR
	private UUID codigo;

	@OneToOne
	private Compra compra;
	
	private BigDecimal valor;
	
	@Column(name = "recdesp", scale = 0)
	private BigDecimal recDesp;

	@ManyToOne
	private Pessoa pessoa;

	public Movimentacao() {

	}

	public Movimentacao(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public UUID getCodigo() {
		return codigo;
	}

	public void setCodigo(UUID codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getRecDesp() {
		return recDesp;
	}

	public void setRecDesp(TipoOperacaoEnum recDesp) {
		this.recDesp = recDesp.getValue();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}
