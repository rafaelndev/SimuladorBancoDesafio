package com.cap.banking.BankSimpleSimulator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cap.banking.BankSimpleSimulator.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	
	List<Transacao> findAllByContaIdOrderByDataDesc(@Param("conta_id") Long conta_id);
}
