package com.spectrum.customer.rewards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spectrum.customer.rewards.model.CustomerResource;
import com.spectrum.customer.rewards.service.CustomerService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping("/customers")
  public ResponseEntity<Void> saveCustomer(@RequestBody @Validated CustomerResource customer) {
    customerService.saveCustomer(customer);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/customers/{customerId}")
  public ResponseEntity<CustomerResource> getCustomer(@PathVariable Long customerId) {
    return new ResponseEntity<>(customerService.getCustomer(customerId), HttpStatus.OK);
  }

  @DeleteMapping("/customers/{customerId}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
    customerService.deleteCustomer(customerId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/customers/{customerId}")
  public ResponseEntity<CustomerResource> updateCustomer(@PathVariable Long customerId,
      @RequestBody @Validated CustomerResource customerResource) {
    return new ResponseEntity<>(customerService.updateCustomer(customerId, customerResource), HttpStatus.OK);
  }

}
