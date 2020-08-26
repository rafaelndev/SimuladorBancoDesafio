package com.cap.banking.BankSimpleSimulator.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cap.banking.BankSimpleSimulator.errorhandling.ContaNotFoundException;
import com.cap.banking.BankSimpleSimulator.errorhandling.MovimentacaoException;
import com.cap.banking.BankSimpleSimulator.model.Conta;
import com.cap.banking.BankSimpleSimulator.model.Movimentacao;
import com.cap.banking.BankSimpleSimulator.model.Transacao;
import com.cap.banking.BankSimpleSimulator.model.Usuario;
import com.cap.banking.BankSimpleSimulator.repository.ContaRepository;
import com.cap.banking.BankSimpleSimulator.repository.TransacaoRepository;

@Service
public class ContaService {

	private final ContaRepository contaRepository;
	private final TransacaoRepository transacaoRepository;
	private final UsuarioService usuarioService;
	
	public ContaService(ContaRepository contaRepository,
			            TransacaoRepository transacaoRepository,
			            UsuarioService usuarioService) {
		this.contaRepository = contaRepository;
		this.transacaoRepository = transacaoRepository;
		this.usuarioService = usuarioService;
	}
	
	public List<Conta> findAll(){
		return contaRepository.findAll();
	}

	public Conta findById(Long id){
		return contaRepository.findById(id)
				.orElseThrow(() -> new ContaNotFoundException(id));
	}
	
	public Conta save(Conta conta) {
		return this.contaRepository.save(conta);
	}
	
	@Transactional
	public int deposito(Movimentacao mov) {

		Conta conta = findContaByUsuarioLogado();

		if (mov.getQuantia() == null || mov.getQuantia().floatValue() <= 0L) {
			throw new MovimentacaoException("Ocorreu uma falha ao tentar realizar o deposito, verifique o valor digitado.");
		}

		BigDecimal saldoAtual = conta.getSaldo();
		BigDecimal novoSaldo = saldoAtual.add(mov.getQuantia());

		Transacao transacao = new Transacao();
		transacao.setConta(conta);
		transacao.setData(new Date());
		transacao.setQuantia(mov.getQuantia());
		transacao.setDescricao(TransacaoTipo.DEPOSITO.getTipo());
		transacao.setSaldoInicial(saldoAtual);
		transacao.setSaldoFinal(novoSaldo);
		transacaoRepository.save(transacao);
		
		return contaRepository.updateSaldo(conta.getId(), novoSaldo);
	}

	@Transactional
	public int saque(Movimentacao mov) {

		Conta conta = findContaByUsuarioLogado();

		if (mov.getQuantia() == null || mov.getQuantia().floatValue() <= 0L) {
			throw new MovimentacaoException("Ocorreu uma falha ao tentar realizar o saque, verifique o valor digitado.");
		}
		// Conta com saldo menor do que o valor do saque
		if (conta.getSaldo().compareTo(mov.getQuantia()) == -1) {
			throw new MovimentacaoException("Você não possui dinheiro suficiente em conta para realizar esse saque.");
		}

		BigDecimal saldoAtual = conta.getSaldo();
		BigDecimal novoSaldo = saldoAtual.subtract(mov.getQuantia());

		Transacao transacao = new Transacao();
		transacao.setConta(conta);
		transacao.setData(new Date());
		transacao.setQuantia(mov.getQuantia());
		transacao.setDescricao(TransacaoTipo.SAQUE.getTipo());
		transacao.setSaldoInicial(saldoAtual);
		transacao.setSaldoFinal(novoSaldo);
		transacaoRepository.save(transacao);

		return contaRepository.updateSaldo(conta.getId(), novoSaldo);
	}
	
	public List<Transacao> listaTransacoes() {

		Conta conta = findContaByUsuarioLogado();
		return this.transacaoRepository.findAllByContaIdOrderByDataDesc(conta.getId());
	}
	
	public Conta findContaByUsuarioLogado() {
		Usuario user = this.usuarioService.getUsuarioLogado();
		return this.contaRepository.findByUsuarioId(user.getId())
				.orElseThrow(() -> new ContaNotFoundException());
	}
}
