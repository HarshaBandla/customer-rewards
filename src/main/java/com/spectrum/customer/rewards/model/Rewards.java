package com.spectrum.customer.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Rewards {

  private final Long customerId;
  private final double rewardValue;

}
