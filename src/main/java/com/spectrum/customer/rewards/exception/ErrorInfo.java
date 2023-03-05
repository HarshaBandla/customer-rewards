package com.spectrum.customer.rewards.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorInfo {
  private final String url;
  private final String message;
}
