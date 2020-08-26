package com.cap.banking.BankSimpleSimulator.services;

public enum TransacaoTipo {
	
	
	SAQUE("Saque"),
	DEPOSITO("Deposito");

	private String tipo;

	TransacaoTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	
}
