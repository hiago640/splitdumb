package br.com.hiago640.splitdumb.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -1195022911056262828L;

	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char") // Esta linha configura a coluna como VARCHAR
	private UUID codigo;

	private String nome;
	private String email;
	private String senha;

	@ManyToMany(mappedBy = "participantes")
	private Set<Grupo> grupos;

	public UUID getCodigo() {
		return codigo;
	}

	public void setCodigo(UUID codigo) {
		this.codigo = codigo;
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
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return String.format("Pessoa [codigo=%s, nome=%s, email=%s, senha=%s]", codigo, nome, email, senha);
	}

}
