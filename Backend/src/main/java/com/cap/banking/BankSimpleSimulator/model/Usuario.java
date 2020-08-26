package com.cap.banking.BankSimpleSimulator.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	@JsonIgnore
	private Long id;
	
	@JsonIgnore
	@Column(nullable = false)
	private String usuario;

	@JsonIgnore
	@Column(nullable = false)
	private String senha;
	private String primeiroNome;
	private String segundoNome;
	private String email;
	private String telefone;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conta_id", referencedColumnName = "id")
	private Conta conta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	@JsonProperty
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSegundoNome() {
		return segundoNome;
	}

	public void setSegundoNome(String segundoNome) {
		this.segundoNome = segundoNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", senha=" + senha + ", primeiroNome=" + primeiroNome
				+ ", segundoNome=" + segundoNome + ", email=" + email + ", telefone=" + telefone + ", conta=" + conta
				+ "]";
	}
}
