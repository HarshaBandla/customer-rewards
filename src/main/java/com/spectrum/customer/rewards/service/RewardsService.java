package com.spectrum.customer.rewards.service;

import java.time.Month;
import java.time.Year;

import com.spectrum.customer.rewards.model.Rewards;

public interface RewardsService {

  Rewards getTotalRewards(Long customerId);
  Rewards getRewardsByYearMonth(Month month, Year year, Long customerId);
  Rewards getRewardsByYear(Year year, Long customerId);

}
