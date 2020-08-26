package com.cap.banking.BankSimpleSimulator.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@SequenceGenerator(name="accountSequence", initialValue=1, allocationSize=100)
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	
	private BigDecimal saldo;
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transacao> transacoes;
    
    @OneToOne(mappedBy = "conta")
    private Usuario usuario;
	
    public Conta() {
    	this.saldo = BigDecimal.ZERO;
    }


	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonInclude
	public String getNumeroConta() {
		if (this.id != null) {
		return String.format("%06d", id);
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", NumeroConta=" + getNumeroConta() + ", saldo=" + saldo + "]" ;
	}

}
