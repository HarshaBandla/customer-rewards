package com.spectrum.customer.rewards.controller;

import com.spectrum.customer.rewards.model.CustomerResource;
import com.spectrum.customer.rewards.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void saveCustomer() {
        CustomerResource customer = new CustomerResource(1L, "John", "Doe", "johndoe@example.com", "1234567890");
        ResponseEntity<Void> response = customerController.saveCustomer(customer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(customerService).saveCustomer(any(CustomerResource.class));
    }

    @Test
    void getCustomer() {
        Long customerId = 1L;
        CustomerResource customer = new CustomerResource(1L, "John", "Doe", "johndoe@example.com", "1234567890");
        when(customerService.getCustomer(customerId)).thenReturn(customer);
        EntityModel<CustomerResource> response = customerController.getCustomer(customerId);
        assertEquals(customer, response.getContent());
    }

    @Test
    void deleteCustomer() {
        Long customerId = 1L;
        ResponseEntity<Void> response = customerController.deleteCustomer(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService).deleteCustomer(customerId);
    }

    @Test
    void updateCustomer() {
        Long customerId = 1L;
        CustomerResource customer = new CustomerResource(1L, "John", "Doe", "johndoe@example.com", "1234567890");
        when(customerService.updateCustomer(customerId, customer)).thenReturn(customer);
        EntityModel<CustomerResource> response = customerController.updateCustomer(customerId, customer);
        assertEquals(customer, response.getContent());
    }
}