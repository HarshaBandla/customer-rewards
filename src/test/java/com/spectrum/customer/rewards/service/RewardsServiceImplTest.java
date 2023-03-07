package com.spectrum.customer.rewards.service;

import com.spectrum.customer.rewards.model.Rewards;
import com.spectrum.customer.rewards.model.TransactionResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RewardsServiceImplTest {

    @Mock
    private CustomerService customerService;
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private RewardsServiceImpl rewardsService;

    @Test
    void testGetTotalRewards() {
        Long customerId = 123L;
        double amount1 = 80.0;
        double amount2 = 120.0;
        TransactionResource transaction1 = new TransactionResource(1L, customerId, amount1, LocalDate.now());
        TransactionResource transaction2 = new TransactionResource(2L, customerId, amount2, LocalDate.now());
        List<TransactionResource> transactions = Arrays.asList(transaction1, transaction2);

        Mockito.when(transactionService.getAllTransactionsForCustomer(customerId))
                .thenReturn(transactions);

        Rewards result = rewardsService.getTotalRewards(customerId);

        assertEquals(120.0, result.getRewardValue());
    }

    @Test
    void testGetRewardsByYearMonth() {
        Long customerId = 123L;
        Month month = Month.JANUARY;
        Year year = Year.now();
        double amount1 = 80.0;
        double amount2 = 120.0;
        TransactionResource transaction1 = new TransactionResource(1L, customerId, amount1, LocalDate.of(year.getValue(), month, 1));
        TransactionResource transaction2 = new TransactionResource(2L, customerId, amount2,  LocalDate.of(year.getValue(), month, 2));
        List<TransactionResource> transactions = Arrays.asList(transaction1, transaction2);

        Mockito.when(transactionService.getTransactionsForCustomerByYearAndMonth(customerId, month, year))
                .thenReturn(transactions);

        Rewards result = rewardsService.getRewardsByYearMonth(month, year, customerId);

        assertEquals(120.0, result.getRewardValue());
    }

    @Test
    void testGetRewardsByYear() {
        Long customerId = 123L;
        Year year = Year.now();
        double amount1 = 80.0;
        double amount2 = 120.0;
        TransactionResource transaction1 = new TransactionResource(1L, customerId, amount1, LocalDate.of(year.getValue(), Month.JANUARY, 1));
        TransactionResource transaction2 = new TransactionResource(2L, customerId, amount2,  LocalDate.of(year.getValue(), Month.FEBRUARY, 2));
        List<TransactionResource> transactions = Arrays.asList(transaction1, transaction2);

        Mockito.when(transactionService.getTransactionsForCustomerByYear(customerId, year))
                .thenReturn(transactions);

        Rewards result = rewardsService.getRewardsByYear(year, customerId);

        assertEquals(120, result.getRewardValue());
    }
}
