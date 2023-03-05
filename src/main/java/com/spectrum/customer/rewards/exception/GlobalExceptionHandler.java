package com.spectrum.customer.rewards.exception;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomerNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorInfo handleCustomerNotFoundException(HttpServletRequest request, HttpServletResponse response,
      Exception ex) {
    return new ErrorInfo(request.getRequestURI(), ex.getMessage());
  }

  @ExceptionHandler(TransactionNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorInfo handleTransactionNotFoundException(HttpServletRequest request, HttpServletResponse response,
      Exception ex) {
    return new ErrorInfo(request.getRequestURI(), ex.getMessage());
  }

  @ExceptionHandler(PropertyValueException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ErrorInfo handleValidationException(HttpServletRequest request, HttpServletResponse response,
      Exception ex) {
    return new ErrorInfo(request.getRequestURI(), ex.getMessage());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ErrorInfo handleDuplicateResourceException(HttpServletRequest request, HttpServletResponse response,
      Exception ex) {
    return new ErrorInfo(request.getRequestURI(), ex.getMessage());
  }

}
