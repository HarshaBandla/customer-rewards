package com.spectrum.customer.rewards.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerNotFoundExceptionTest {

    @Test
    void testCustomerNotFoundException() {

        String message = "Customer not found";
        CustomerNotFoundException exception = new CustomerNotFoundException(message);

        assertNotNull(message);
        assertEquals(message, exception.getMessage());
    }

}