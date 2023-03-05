package com.spectrum.customer.rewards.model;

import com.spectrum.customer.rewards.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CustomerResource {

  private final Long id;
  @NotNull
  private final String firstName;
  @NotNull
  private final String lastName;
  @NotNull
  private final String email;
  @NotNull
  private final String phoneNumber;

  public static CustomerResource buildFromCustomerEntity(Customer customer) {
    return CustomerResource.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .phoneNumber(customer.getPhoneNumber())
        .build();
  }

  public static Customer buildCustomerEntity(CustomerResource customer) {
    return Customer.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .phoneNumber(customer.getPhoneNumber())
        .build();
  }

}
