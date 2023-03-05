package com.spectrum.customer.rewards.service;

import java.time.Month;
import java.time.Year;
import java.util.List;

import com.spectrum.customer.rewards.entity.Transaction;
import com.spectrum.customer.rewards.model.TransactionResource;

public interface TransactionService {

  void saveTransaction(TransactionResource transaction);
  TransactionResource updateTransaction(Long transactionId, TransactionResource transaction);
  TransactionResource getTransaction(Long transactionId);
  void deleteTransaction(Long transactionId);
  List<TransactionResource> getAllTransactionsForCustomer(Long customerId);

  List<TransactionResource> getTransactionsForCustomerByYearAndMonth(Long customerId, Month month, Year year);

  List<TransactionResource> getTransactionsForCustomerByYear(Long customerId, Year year);

}
