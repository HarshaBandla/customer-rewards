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

import com.spectrum.customer.rewards.model.TransactionResource;
import com.spectrum.customer.rewards.service.CustomerService;
import com.spectrum.customer.rewards.service.TransactionService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;
  private final CustomerService customerService;

  @PostMapping("/transactions")
  public ResponseEntity<Void> saveTransaction(@RequestBody @Validated TransactionResource transaction) {
    customerService.getCustomer(transaction.getCustomerId());
    transactionService.saveTransaction(transaction);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/transactions/{transactionId}")
  public EntityModel<TransactionResource> getTransaction(@PathVariable Long transactionId) {
    Link self = linkTo(methodOn(TransactionController.class).getTransaction(transactionId)).withSelfRel();
    return EntityModel.of(transactionService.getTransaction(transactionId), self);
  }

  @DeleteMapping("/transactions/{transactionId}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
    transactionService.deleteTransaction(transactionId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/transactions/{transactionId}")
  public EntityModel<TransactionResource> updateTransaction(@PathVariable Long transactionId,
      @RequestBody @Validated TransactionResource transaction) {
    Link self = linkTo(methodOn(TransactionController.class).getTransaction(transactionId)).withSelfRel();
    return EntityModel.of(transactionService.updateTransaction(transactionId, transaction), self);
  }

}
