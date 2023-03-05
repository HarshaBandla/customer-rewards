package com.spectrum.customer.rewards.model;

import java.time.LocalDate;

import com.spectrum.customer.rewards.entity.Transaction;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TransactionResource {

  private final Long id;
  @NotNull
  private final Long customerId;
  @NotNull
  private final double amount;
  @NotNull
  private final LocalDate transactionDate;

  public static TransactionResource buildFromTransactionEntity(Transaction transaction) {
    return TransactionResource.builder()
        .id(transaction.getId())
        .customerId(transaction.getCustomerId())
        .amount(transaction.getAmount())
        .transactionDate(transaction.getTransactionDate())
        .build();
  }

  public static Transaction buildTransactionEntity(TransactionResource transaction) {
    return Transaction.builder()
        .id(transaction.getId())
        .customerId(transaction.getCustomerId())
        .amount(transaction.getAmount())
        .transactionDate(transaction.getTransactionDate())
        .build();
  }
}
