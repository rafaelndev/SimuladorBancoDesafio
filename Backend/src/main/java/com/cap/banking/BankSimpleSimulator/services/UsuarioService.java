package com.cap.banking.BankSimpleSimulator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cap.banking.BankSimpleSimulator.config.security.UsuarioPrincipal;
import com.cap.banking.BankSimpleSimulator.errorhandling.UsuarioNotFoundException;
import com.cap.banking.BankSimpleSimulator.model.Conta;
import com.cap.banking.BankSimpleSimulator.model.Usuario;
import com.cap.banking.BankSimpleSimulator.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	AuthenticationManager authManager;
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException(String.valueOf(id)));
	}

	public Usuario findByUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario)
				.orElseThrow(() -> new UsuarioNotFoundException(usuario));
	}
	
	public Usuario save(Usuario usuario) {
		Conta conta = new Conta();

		System.out.println(usuario);
		usuario.setConta(conta);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

		return usuarioRepository.save(usuario);
	}

	public Usuario getUsuarioLogado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			UsuarioPrincipal user = (UsuarioPrincipal) principal;
			return user.getUsuario();
		}

		return null;
	}
	
}
