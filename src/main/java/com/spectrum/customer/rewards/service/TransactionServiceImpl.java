package com.spectrum.customer.rewards.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spectrum.customer.rewards.entity.Transaction;
import com.spectrum.customer.rewards.exception.TransactionNotFoundException;
import com.spectrum.customer.rewards.model.TransactionResource;
import com.spectrum.customer.rewards.repository.TransactionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  @Override
  public void saveTransaction(TransactionResource transaction) {
    transactionRepository.save(TransactionResource.buildTransactionEntity(transaction));
  }

  @Override
  public TransactionResource updateTransaction(Long transactionId, TransactionResource transactionResource) {
    Transaction transaction = transactionRepository.findById(transactionId)
        .orElseThrow(() -> new TransactionNotFoundException(String.format("Transaction %d not exists", transactionId)));
    TransactionResource updateTransaction = new TransactionResource(transaction.getId(),
        transactionResource.getCustomerId(),
        transactionResource.getAmount(), transactionResource.getTransactionDate());
    transactionRepository.save(TransactionResource.buildTransactionEntity(updateTransaction));
    return updateTransaction;
  }

  @Override
  public TransactionResource getTransaction(Long transactionId) {
    Transaction transaction = transactionRepository
        .findById(transactionId)
        .orElseThrow(() -> new TransactionNotFoundException(String.format("Transaction %d not exists", transactionId)));
    return TransactionResource.buildFromTransactionEntity(transaction);
  }

  @Override
  public void deleteTransaction(Long transactionId) {
    transactionRepository.deleteById(transactionId);
  }

  @Override
  public List<TransactionResource> getAllTransactionsForCustomer(Long customerId) {
    return transactionRepository
        .findByCustomerId(customerId)
        .stream()
        .map(TransactionResource::buildFromTransactionEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<TransactionResource> getTransactionsForCustomerByYearAndMonth(Long customerId, Month month, Year year) {
    YearMonth yearMonth = YearMonth.of(year.getValue(), month.getValue());
    LocalDate start = yearMonth.atDay(1);
    LocalDate end = yearMonth.atEndOfMonth();
    return transactionRepository
        .findByCustomerIdAndTransactionDateBetween(customerId, start, end).stream()
        .map(TransactionResource::buildFromTransactionEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<TransactionResource> getTransactionsForCustomerByYear(Long customerId, Year year) {
    LocalDate start = YearMonth.of(year.getValue(), 1).atDay(1);
    LocalDate end = YearMonth.of(year.getValue(), 12).atEndOfMonth();
    return transactionRepository
        .findByCustomerIdAndTransactionDateBetween(customerId, start, end).stream()
        .map(TransactionResource::buildFromTransactionEntity)
        .collect(Collectors.toList());
  }

}
