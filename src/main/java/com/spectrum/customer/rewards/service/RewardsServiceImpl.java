package com.spectrum.customer.rewards.service;

import java.time.Month;
import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spectrum.customer.rewards.model.Rewards;
import com.spectrum.customer.rewards.model.TransactionResource;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RewardsServiceImpl implements RewardsService {

  private final CustomerService customerService;
  private final TransactionService transactionService;

  @Override
  public Rewards getTotalRewards(Long customerId) {
    customerService.getCustomer(customerId);
    List<TransactionResource> transactions = transactionService.getAllTransactionsForCustomer(customerId);
    double points = transactions.stream()
        .map(TransactionResource::getAmount)
        .map(this::calculatePoints)
        .mapToDouble(Double::doubleValue)
        .sum();
    return new Rewards(customerId, points);
  }

  @Override
  public Rewards getRewardsByYearMonth(Month month, Year year, Long customerId) {
    customerService.getCustomer(customerId);
    List<TransactionResource> transactions = transactionService
        .getTransactionsForCustomerByYearAndMonth(customerId, month, year);
    double points = transactions.stream()
        .map(TransactionResource::getAmount)
        .map(this::calculatePoints)
        .mapToDouble(Double::doubleValue)
        .sum();
    return new Rewards(customerId, points);
  }

  @Override
  public Rewards getRewardsByYear(Year year, Long customerId) {
    customerService.getCustomer(customerId);
    List<TransactionResource> transactions = transactionService
        .getTransactionsForCustomerByYear(customerId, year);
    double points = transactions.stream()
        .map(TransactionResource::getAmount)
        .map(this::calculatePoints)
        .mapToDouble(Double::doubleValue)
        .sum();
    return new Rewards(customerId, points);
  }

  private double calculatePoints(double amount) {
    double points = 0;
    if (amount > 50 && amount < 100) {
      points = points + amount - 50;
    }
    if (amount >= 100) {
      points = points + 50 + 2 * (amount - 100);
    }
    return points;
  }

}
