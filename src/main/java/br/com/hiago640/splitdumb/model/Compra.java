package br.com.hiago640.splitdumb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {

	private static final long serialVersionUID = -4732330819981756040L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	@NotBlank(message = "O Nome do Compra deve ser preenchido.")
	private String descricao;

//	@Min(value = 0, message = "O valor mínimo da compra é 0(Zero)")
	private BigDecimal valorCompra;

	@ManyToOne
	@JsonBackReference
	private Pessoa comprador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grupo_id")
	@JsonBackReference
	private Grupo grupo;

	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "compra_envolvido")
	private List<Pessoa> envolvidos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pessoa getComprador() {
		return comprador;
	}

	public void setComprador(Pessoa comprador) {
		this.comprador = comprador;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public List<Pessoa> getEnvolvidos() {
		return envolvidos;
	}

	public void setEnvolvidos(List<Pessoa> envolvidos) {
		this.envolvidos = envolvidos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return String.format("Compra [id=%s, descricao=%s, valorCompra=%s, comprador=%s]", id,
				descricao, valorCompra, comprador);
	}

}
