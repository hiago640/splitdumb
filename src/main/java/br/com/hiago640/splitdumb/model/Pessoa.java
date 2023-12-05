package br.com.hiago640.splitdumb.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -1195022911056262828L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	@NotBlank(message = "O Nome deve ser preenchido.")
	private String nome;
	private String email;
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pessoa_grupo", joinColumns = @JoinColumn(name = "pessoa_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	@JsonManagedReference
	private Set<Grupo> grupos;

	@OneToMany(mappedBy = "comprador")
	private Set<Compra> compras;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	@Override
	public String toString() {
		return String.format("Pessoa [id=%s, nome=%s, email=%s]", id, nome, email);
	}

}
