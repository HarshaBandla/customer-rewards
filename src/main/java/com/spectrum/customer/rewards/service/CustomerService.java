package com.spectrum.customer.rewards.service;

import com.spectrum.customer.rewards.entity.Customer;
import com.spectrum.customer.rewards.model.CustomerResource;

public interface CustomerService {

  void saveCustomer(CustomerResource customer);
  CustomerResource getCustomer(Long customerId);
  void deleteCustomer(Long customerId);
  CustomerResource updateCustomer(Long customerId, CustomerResource customerResource);

}
