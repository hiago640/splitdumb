package br.com.hiago640.splitdumb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {

	private static final long serialVersionUID = -4732330819981756040L;

	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char") // Esta linha configura a coluna como VARCHAR
	private UUID codigo;

	private String descricao;
	
	@Column(name = "vlrcompra")
	private BigDecimal valorCompra;

	@ManyToOne
	private Pessoa comprador;

	@ManyToMany
	private List<Pessoa> envolvidos;

	@ManyToOne
	private Grupo grupo;

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
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Compra))
			return false;
		Compra other = (Compra) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return String.format("Compra [codigo=%s, descricao=%s, valorCompra=%s]", codigo, descricao, valorCompra);
	}

}
