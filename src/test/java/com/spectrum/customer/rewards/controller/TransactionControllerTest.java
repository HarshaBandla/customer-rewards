package com.spectrum.customer.rewards.controller;

import com.spectrum.customer.rewards.model.TransactionResource;
import com.spectrum.customer.rewards.service.CustomerService;
import com.spectrum.customer.rewards.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void testSaveTransaction() {

        TransactionResource transaction = new TransactionResource(1L, 1L, 100.0, LocalDate.now());

        ResponseEntity<Void> responseEntity = transactionController.saveTransaction(transaction);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(transactionService, times(1)).saveTransaction(transaction);
    }

    @Test
    void testGetTransaction() {

        TransactionResource transaction = new TransactionResource(1L, 1L, 100.0, LocalDate.now());

        when(transactionService.getTransaction(1L)).thenReturn(transaction);

        EntityModel<TransactionResource> responseEntity = transactionController.getTransaction(1L);

        assertEquals(transaction, responseEntity.getContent());
        verify(transactionService, times(1)).getTransaction(1L);
    }

    @Test
    void testDeleteTransaction() {

        ResponseEntity<Void> responseEntity = transactionController.deleteTransaction(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(transactionService, times(1)).deleteTransaction(1L);
    }

    @Test
    void testUpdateTransaction() {

        TransactionResource transaction = new TransactionResource(1L, 1L, 100.0, LocalDate.now());

        when(transactionService.updateTransaction(1L, transaction)).thenReturn(transaction);

        EntityModel<TransactionResource> responseEntity = transactionController.updateTransaction(1L, transaction);

        assertEquals(transaction, responseEntity.getContent());
        verify(transactionService, times(1)).updateTransaction(1L, transaction);
    }
}