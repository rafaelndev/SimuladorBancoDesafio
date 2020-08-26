package com.cap.banking.BankSimpleSimulator.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cap.banking.BankSimpleSimulator.errorhandling.ContaNotFoundException;
import com.cap.banking.BankSimpleSimulator.model.Conta;
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
	public int deposito(Long id, BigDecimal valor) {
		Conta contaAfetada = findById(id);
		BigDecimal saldoAtual = contaAfetada.getSaldo();
		BigDecimal novoSaldo = saldoAtual.add(valor);

		Transacao transacao = new Transacao();
		transacao.setConta(contaAfetada);
		transacao.setData(new Date());
		transacao.setQuantia(valor);
		transacao.setDescricao("Deposito");
		transacao.setSaldoInicial(saldoAtual);
		transacao.setSaldoFinal(novoSaldo);
		transacaoRepository.save(transacao);
		
		return contaRepository.updateSaldo(id, novoSaldo);
	}

	@Transactional
	public int saque(Long id, BigDecimal valor) {
		Conta contaAfetada = findById(id);
		BigDecimal saldoAtual = contaAfetada.getSaldo();
		BigDecimal novoSaldo = saldoAtual.subtract(valor);

		Transacao transacao = new Transacao();
		transacao.setConta(contaAfetada);
		transacao.setData(new Date());
		transacao.setQuantia(valor);
		transacao.setDescricao("Saque");
		transacao.setSaldoInicial(saldoAtual);
		transacao.setSaldoFinal(novoSaldo);
		transacaoRepository.save(transacao);
		return contaRepository.updateSaldo(id, novoSaldo);
	}
	
	public List<Transacao> listaTransacoes(Long conta_id) {
		return this.transacaoRepository.findAllByContaIdOrderByDataDesc(conta_id);
	}
	
	public Conta findContaByUsuarioLogado() {
		Usuario user = this.usuarioService.getUsuarioLogado();
		return this.contaRepository.findByUsuarioId(user.getId())
				.orElseThrow(() -> new ContaNotFoundException());
	}
}
