package br.com.hiago640.estudo.novo_splitdumb.model;

import java.util.Objects;

public class Usuario {

	private String uuid;
	private String nomeUsu;
	private String email;

	public Usuario(String uuid, String nomeUsu, String email) {
		this.uuid = uuid;
		this.nomeUsu = nomeUsu;
		this.email = email;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNomeUsu() {
		return nomeUsu;
	}

	public void setNomeUsu(String nomeUsu) {
		this.nomeUsu = nomeUsu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return String.format("Usuario [uuid=%s nomeUsu=%s, email=%s]", uuid,nomeUsu, email);
	}
	
}
