package com.spectrum.customer.rewards.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spectrum.customer.rewards.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findByCustomerId(Long customerId);
  List<Transaction> findByCustomerIdAndTransactionDateBetween(Long customerId, LocalDate from, LocalDate to);

}
