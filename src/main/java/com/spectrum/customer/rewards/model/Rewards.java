package com.spectrum.customer.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class Rewards extends RepresentationModel<Rewards> {

  private final Long customerId;
  private final double rewardValue;

}
