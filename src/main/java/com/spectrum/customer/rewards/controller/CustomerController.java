package com.spectrum.customer.rewards.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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
  public EntityModel<CustomerResource> getCustomer(@PathVariable Long customerId) {
    Link customerLink = linkTo(methodOn(CustomerController.class).getCustomer(customerId)).withSelfRel();
    Link createTransactionLink = linkTo(methodOn(TransactionController.class).saveTransaction(null)).withRel(
        "transactions.create");
    Link getRewardsLink = linkTo(methodOn(RewardsController.class).getRewardsForCustomer(customerId, null, null))
        .withRel("customer.rewards");
    return EntityModel.of(customerService.getCustomer(customerId), customerLink, createTransactionLink, getRewardsLink);
  }

  @DeleteMapping("/customers/{customerId}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
    customerService.deleteCustomer(customerId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/customers/{customerId}")
  public EntityModel<CustomerResource> updateCustomer(@PathVariable Long customerId,
      @RequestBody @Validated CustomerResource customerResource) {
    Link customerLink = linkTo(methodOn(CustomerController.class).getCustomer(customerId)).withSelfRel();
    Link createTransactionLink = linkTo(methodOn(TransactionController.class).saveTransaction(null)).withRel(
        "transactions.create");
    Link getRewardsLink = linkTo(methodOn(RewardsController.class).getRewardsForCustomer(customerId, null, null))
        .withRel("customer.rewards");
    return EntityModel.of(customerService.updateCustomer(customerId, customerResource), customerLink,
        createTransactionLink, getRewardsLink);
  }

}
