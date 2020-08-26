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
		Conta conta = contaService.findContaByUsuarioLogado();

		if (mov.getQuantia() == null || mov.getQuantia().floatValue() <= 0L) {
			throw new MovimentacaoException("Ocorreu uma falha ao tentar realizar o deposito, verifique o valor digitado.");
		}
		contaService.deposito(conta.getId(), mov.getQuantia());
	}

	@PostMapping("/conta/saque")
	void saque(@RequestBody Movimentacao mov) {
		Conta conta = contaService.findContaByUsuarioLogado();

		if (mov.getQuantia() == null || mov.getQuantia().floatValue() <= 0L) {
			throw new MovimentacaoException("Ocorreu uma falha ao tentar realizar o saque, verifique o valor digitado.");
		}
		// Conta com saldo menor do que o valor do saque
		if (conta.getSaldo().compareTo(mov.getQuantia()) == -1) {
			throw new MovimentacaoException("Você não possui dinheiro suficiente em conta para realizar esse saque.");
		}
		contaService.saque(conta.getId(), mov.getQuantia());
	}
	
	@GetMapping("conta/transacoes")
	List<Transacao> listaTransacoes() {
		Conta conta = contaService.findContaByUsuarioLogado();
		return contaService.listaTransacoes(conta.getId());
	}
}
