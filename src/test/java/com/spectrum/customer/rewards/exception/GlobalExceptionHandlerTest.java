package com.spectrum.customer.rewards.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class GlobalExceptionHandlerTest {

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleCustomerNotFoundException() {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CustomerNotFoundException ex = new CustomerNotFoundException("Customer not found");

        ErrorInfo errorInfo = globalExceptionHandler.handleCustomerNotFoundException(request, response, ex);

        assertNotNull(errorInfo);
        assertEquals("Customer not found", errorInfo.getMessage());
        assertEquals(request.getRequestURI(), errorInfo.getUrl());

    }

    @Test
    void testHandleTransactionNotFoundException() {

        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        TransactionNotFoundException ex = new TransactionNotFoundException("Transaction not found");

        ErrorInfo errorInfo = globalExceptionHandler.handleTransactionNotFoundException(request, response, ex);

        assertNotNull(errorInfo);
        assertEquals("Transaction not found", errorInfo.getMessage());
        assertEquals(request.getRequestURI(), errorInfo.getUrl());

    }

    @Test
    void testHandleValidationException() {

        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        PropertyValueException ex = new PropertyValueException("Invalid property value", "Customer", "Middle Name");

        ErrorInfo errorInfo = globalExceptionHandler.handleValidationException(request, response, ex);

        assertNotNull(errorInfo);
        assertEquals(request.getRequestURI(), errorInfo.getUrl());
        assertEquals("Invalid property value : Customer.Middle Name", errorInfo.getMessage());

    }

    @Test
    void testHandleDuplicateResourceException() {

        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Duplicate resource");

        ErrorInfo errorInfo = globalExceptionHandler.handleDuplicateResourceException(request, response, ex);

        assertNotNull(errorInfo);
        assertEquals(request.getRequestURI(), errorInfo.getUrl());
        assertEquals("Duplicate resource", errorInfo.getMessage());

    }

}