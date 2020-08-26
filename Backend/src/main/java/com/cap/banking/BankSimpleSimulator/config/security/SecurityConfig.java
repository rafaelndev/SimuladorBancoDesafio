package com.cap.banking.BankSimpleSimulator.config.security;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private AuthProvider authProvider;

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;

//
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.parentAuthenticationManager(authenticationManagerBean())
		.userDetailsService(this.usuarioDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Inicializando autenticação Basica
		http.authorizeRequests()
			.anyRequest()
				.authenticated()
			.and()
				.httpBasic()
			.and()
				.csrf()
				.disable()
			.cors();

		// Resposta padrão de erro de acesso negado em JSON
		http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			ObjectMapper map = new ObjectMapper();

			ObjectNode res = map.createObjectNode();
			res.put("timestamp", LocalDateTime.now().toString());
			res.put("message", "Acesso Negado");
			res.put("error", String.valueOf(response.getStatus()));
			String json = map.writerWithDefaultPrettyPrinter().writeValueAsString(res);
			response.getWriter().write(json);
		});
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200/"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
//		configuration.addExposedHeader("Authorization");
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2/**", "/login/**", "/logout", "/registro");
	}

	/**
	 * Definindo algoritmo de senha padrão para cadastro e autenticação de usuario
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
