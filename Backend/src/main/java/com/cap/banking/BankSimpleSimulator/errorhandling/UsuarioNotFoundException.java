package com.cap.banking.BankSimpleSimulator.errorhandling;

public class UsuarioNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(String user) {
		super("Não foi possivel encontrar o usuário: " + user);
	}

}
