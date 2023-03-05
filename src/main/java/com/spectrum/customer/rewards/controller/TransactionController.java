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

import com.spectrum.customer.rewards.model.TransactionResource;
import com.spectrum.customer.rewards.service.TransactionService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping("/transactions")
  public ResponseEntity<Void> saveTransaction(@RequestBody @Validated TransactionResource transaction) {
    transactionService.saveTransaction(transaction);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/transactions/{transactionId}")
  public ResponseEntity<TransactionResource> getTransaction(@PathVariable Long transactionId) {
    return new ResponseEntity<>(transactionService.getTransaction(transactionId), HttpStatus.OK);
  }

  @DeleteMapping("/transactions/{transactionId}")
  public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
    transactionService.deleteTransaction(transactionId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/transactions/{transactionId}")
  public ResponseEntity<TransactionResource> updateTransaction(@PathVariable Long transactionId,
      @RequestBody @Validated TransactionResource transaction) {
    return new ResponseEntity<>(transactionService.updateTransaction(transactionId, transaction), HttpStatus.CREATED);
  }

}
