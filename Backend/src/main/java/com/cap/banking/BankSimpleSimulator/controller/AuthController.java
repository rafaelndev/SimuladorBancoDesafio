package com.cap.banking.BankSimpleSimulator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.banking.BankSimpleSimulator.model.Usuario;
import com.cap.banking.BankSimpleSimulator.services.UsuarioService;

@RestController
public class AuthController {
	
	private final UsuarioService usuarioService;
	
	public AuthController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping(path = "/basiclogin")
	public Usuario authenticate() {
		return new Usuario();
	}

}
