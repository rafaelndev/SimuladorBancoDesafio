package com.cap.banking.BankSimpleSimulator.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cap.banking.BankSimpleSimulator.errorhandling.UsuarioNotFoundException;
import com.cap.banking.BankSimpleSimulator.model.Usuario;
import com.cap.banking.BankSimpleSimulator.repository.UsuarioRepository;
import com.cap.banking.BankSimpleSimulator.services.UsuarioService;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = null;
		System.out.println("loadUserByUsername: " + username);

		try {
			usuario = usuarioService.findByUsuario(username);
		} catch (UsuarioNotFoundException e) {
			if (usuario == null) {
				return null;
			}
		}

		return new UsuarioPrincipal(usuario);
	}

}
