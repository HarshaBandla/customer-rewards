package com.spectrum.customer.rewards.service;

import com.spectrum.customer.rewards.entity.Customer;
import com.spectrum.customer.rewards.exception.CustomerNotFoundException;
import com.spectrum.customer.rewards.model.CustomerResource;
import com.spectrum.customer.rewards.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testSaveCustomer() {

        CustomerResource customer = new CustomerResource(1L, "John", "Doe", "johndoe@example.com", "1234567890");

        customerService.saveCustomer(customer);

        verify(customerRepository, times(1)).save(any());
    }

    @Test
    void testGetCustomer() {

        Long customerId = 123L;
        Customer customer = new Customer();
        Customer.builder()
                .id(customerId)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phoneNumber("1234567890")
                .build();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        CustomerResource customerResource = customerService.getCustomer(customerId);

        assertEquals(customerId, customerResource.getId());
        assertEquals("John", customerResource.getFirstName());
        assertEquals("Doe", customerResource.getLastName());
        assertEquals("john.doe@example.com", customerResource.getEmail());
        assertEquals("1234567890", customerResource.getPhoneNumber());
    }

    @Test
    void testGetCustomerNotFound() {

        Long customerId = 123L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer(customerId));
    }


    @Test
    void testDeleteCustomer() {

        Long customerId = 1L;

        customerService.deleteCustomer(customerId);

        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void testUpdateCustomer() {

        Long customerId = 1L;
        Customer existingCustomer = new Customer(customerId, "John", "Doe", "john.doe@example.com", "1234567890");
        CustomerResource customerResource = new CustomerResource(null, "Jane", "Doe", "jane.doe@example.com", "0987654321");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CustomerResource updatedCustomer = customerService.updateCustomer(customerId, customerResource);

        assertNotNull(updatedCustomer);
        assertEquals(existingCustomer.getId(), updatedCustomer.getId());
        assertEquals(customerResource.getFirstName(), updatedCustomer.getFirstName());
        assertEquals(customerResource.getLastName(), updatedCustomer.getLastName());
        assertEquals(customerResource.getEmail(), updatedCustomer.getEmail());
        assertEquals(customerResource.getPhoneNumber(), updatedCustomer.getPhoneNumber());

        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).save(any());
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void testUpdateCustomerWithException() {

        Long customerId = 1L;
        CustomerResource customerResource = new CustomerResource(null, "Jane", "Doe", "jane.doe@example.com", "0987654321");

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(customerId, customerResource));

        verify(customerRepository, times(1)).findById(customerId);
        verifyNoMoreInteractions(customerRepository);
    }
}