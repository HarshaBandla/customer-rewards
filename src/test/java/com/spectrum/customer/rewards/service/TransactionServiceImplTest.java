package com.spectrum.customer.rewards.service;

import com.spectrum.customer.rewards.entity.Transaction;
import com.spectrum.customer.rewards.model.TransactionResource;
import com.spectrum.customer.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testSaveTransaction() {

        TransactionResource transaction = new TransactionResource(1L, 1L, 100.0, LocalDate.now());

        transactionService.saveTransaction(transaction);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testUpdateTransaction() {

        Long transactionId = 1L;
        TransactionResource transaction = new TransactionResource(1L, 1L, 100.0, LocalDate.now());
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(new Transaction()));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());

        transactionService.updateTransaction(transactionId, transaction);

        verify(transactionRepository, times(1)).findById(transactionId);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testGetTransaction() {

        Long transactionId = 1L;
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(new Transaction()));

        transactionService.getTransaction(transactionId);

        verify(transactionRepository, times(1)).findById(transactionId);
    }

    @Test
    void testDeleteTransaction() {

        Long transactionId = 1L;

        transactionService.deleteTransaction(transactionId);

        verify(transactionRepository, times(1)).deleteById(transactionId);
    }

    @Test
    void testGetAllTransactionsForCustomer() {

        Long customerId = 1L;
        when(transactionRepository.findByCustomerId(customerId)).thenReturn(Collections.singletonList(new Transaction()));

        List<TransactionResource> transactions = transactionService.getAllTransactionsForCustomer(customerId);

        verify(transactionRepository, times(1)).findByCustomerId(customerId);
        assertEquals(1, transactions.size());
    }

    @Test
    void testGetTransactionsForCustomerByYearAndMonth() {

        Long customerId = 1L;
        Year year = Year.now();
        Month month = Month.JANUARY;
        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(eq(customerId), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.singletonList(new Transaction()));

        List<TransactionResource> transactions = transactionService.getTransactionsForCustomerByYearAndMonth(customerId, month, year);

        verify(transactionRepository, times(1)).findByCustomerIdAndTransactionDateBetween(eq(customerId), any(LocalDate.class), any(LocalDate.class));
        assertEquals(1, transactions.size());
    }

    @Test
    void testGetTransactionsForCustomerByYear() {

        Long customerId = 1L;
        Year year = Year.now();
        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(eq(customerId), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.singletonList(new Transaction()));

        List<TransactionResource> transactions = transactionService.getTransactionsForCustomerByYear(customerId, year);

        verify(transactionRepository, times(1)).findByCustomerIdAndTransactionDateBetween(eq(customerId), any(LocalDate.class), any(LocalDate.class));
        assertEquals(1, transactions.size());
    }

}