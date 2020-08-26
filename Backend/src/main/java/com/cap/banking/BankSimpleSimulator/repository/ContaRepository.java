package com.cap.banking.BankSimpleSimulator.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cap.banking.BankSimpleSimulator.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	@Modifying(clearAutomatically = true) // Impede problemas de sincronização
	@Query("UPDATE Conta c SET c.saldo = :saldo WHERE c.id = :id")
	int updateSaldo(@Param("id") long id, @Param("saldo") BigDecimal saldo);
	
	Optional<Conta> findByUsuarioId(@Param("usuario_id") long id);
}
