package com.cap.banking.BankSimpleSimulator.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cap.banking.BankSimpleSimulator.config.security.UsuarioPrincipal;
import com.cap.banking.BankSimpleSimulator.errorhandling.UsuarioNotFoundException;
import com.cap.banking.BankSimpleSimulator.model.Usuario;
import com.cap.banking.BankSimpleSimulator.repository.UsuarioRepository;
import com.cap.banking.BankSimpleSimulator.services.UsuarioService;

@RestController
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/usuarios")
	List<Usuario> findAll() {
		return usuarioService.findAll();
	}

	@PostMapping("/registro")
	Usuario newUsuario(@RequestBody Usuario novoUsuario) {
		return usuarioService.save(novoUsuario);
	}

	@GetMapping("/usuario/{id}")
	Usuario getOne(@PathVariable Long id) {
		return usuarioService.findById(id);
	}

}
