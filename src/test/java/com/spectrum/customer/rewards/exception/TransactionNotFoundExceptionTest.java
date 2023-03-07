package com.spectrum.customer.rewards.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionNotFoundExceptionTest {

    @Test
    void testTransactionNotFoundException() {

        String message = "Transaction not found";
        TransactionNotFoundException exception = new TransactionNotFoundException(message);

        assertNotNull(message);
        assertEquals(message, exception.getMessage());
    }

}