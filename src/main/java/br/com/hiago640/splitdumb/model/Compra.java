package br.com.hiago640.splitdumb.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "compras")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O Nome do Compra deve ser preenchido.")
	private String nomeCompra;

	@OneToOne
	private Pessoa comprador;

	@Min(value = 0, message = "O valor mínimo da compra é 0(Zero)")
	private BigDecimal valorCompra;

	@OneToMany
	private List<Pessoa> envolvidos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeCompra() {
		return nomeCompra;
	}

	public void setNomeCompra(String nomeCompra) {
		this.nomeCompra = nomeCompra;
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

	@Override
	public String toString() {
		return String.format("Compra [id=%s, nomeCompra=%s, comprador=%s, valorCompra=%s, envolvidos=%s]", id,
				nomeCompra, comprador, valorCompra, envolvidos);
	}

}
