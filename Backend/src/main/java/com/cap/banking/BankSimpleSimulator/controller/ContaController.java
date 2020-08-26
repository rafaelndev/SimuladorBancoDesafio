package com.cap.banking.BankSimpleSimulator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cap.banking.BankSimpleSimulator.errorhandling.MovimentacaoException;
import com.cap.banking.BankSimpleSimulator.model.Conta;
import com.cap.banking.BankSimpleSimulator.model.Movimentacao;
import com.cap.banking.BankSimpleSimulator.model.Transacao;
import com.cap.banking.BankSimpleSimulator.services.ContaService;

@RestController
public class ContaController {

	private final ContaService contaService;
	
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	
	@GetMapping("/contas")
	List<Conta> findAll() {
		return contaService.findAll();
	}
	
	@PostMapping("/contas")
	Conta newConta(@RequestBody Conta novaConta) {
		return contaService.save(novaConta);
	}
	
	@GetMapping("/conta")
	Conta findById() {
		Conta conta = contaService.findContaByUsuarioLogado();
		return conta;
	}

	@PostMapping("/conta/deposito")
	void deposito(@RequestBody Movimentacao mov) {
		contaService.deposito(mov);
	}

	@PostMapping("/conta/saque")
	void saque(@RequestBody Movimentacao mov) {
		contaService.saque(mov);
	}
	
	@GetMapping("conta/transacoes")
	List<Transacao> listaTransacoes() {
		return contaService.listaTransacoes();
	}
}
