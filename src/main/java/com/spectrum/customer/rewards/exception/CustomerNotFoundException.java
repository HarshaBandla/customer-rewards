package com.spectrum.customer.rewards.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  public CustomerNotFoundException(String message) {
    super(message);
  }
}
