package com.cap.banking.BankSimpleSimulator.errorhandling;

public class ContaNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContaNotFoundException(Long id) {
		super("Não foi possivel encontrar a conta: " + id);
	}

	public ContaNotFoundException() {
		super("Não foi possivel encontrar a conta");
	}

}
