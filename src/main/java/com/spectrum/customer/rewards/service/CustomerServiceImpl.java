package com.spectrum.customer.rewards.service;

import org.springframework.stereotype.Service;

import com.spectrum.customer.rewards.entity.Customer;
import com.spectrum.customer.rewards.exception.CustomerNotFoundException;
import com.spectrum.customer.rewards.model.CustomerResource;
import com.spectrum.customer.rewards.repository.CustomerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public void saveCustomer(CustomerResource customer) {
    customerRepository.save(CustomerResource.buildCustomerEntity(customer));
  }

  @Override
  public CustomerResource getCustomer(Long customerId) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer %d not exists", customerId)));
    return CustomerResource.buildFromCustomerEntity(customer);
  }

  @Override
  public void deleteCustomer(Long customerId) {
    customerRepository.deleteById(customerId);
  }

  @Override
  public CustomerResource updateCustomer(Long customerId, CustomerResource customerResource) {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer %d not exists", customerId)));
    CustomerResource updateCustomer = new CustomerResource(customer.getId(), customerResource.getFirstName(),
        customerResource.getLastName(), customerResource.getEmail(), customerResource.getPhoneNumber());
    customerRepository.save(CustomerResource.buildCustomerEntity(updateCustomer));
    return updateCustomer;
  }

}
