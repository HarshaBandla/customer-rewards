package com.spectrum.customer.rewards.controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spectrum.customer.rewards.model.Rewards;
import com.spectrum.customer.rewards.service.RewardsService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RewardsController {

  private final RewardsService rewardsService;

  /**
   * @param customerId The unique identifier of the customer whose rewards information is being requested.
   * @param month The month for which the rewards information is being requested. This parameter is optional, but if it
   * is provided, it should be a valid integer between 1 and 12, corresponding to the month of the year (e.g. 1 for
   * January, 2 for February, etc.).
   * @param year The year for which the rewards information is being requested. This parameter is optional, but if it is
   * provided, it should be a valid 4-digit integer (e.g. 2022).
   * @return The rewards information for the specified customer for the specified month and year. When month and year
   * are not provided, total rewards of customer is returned. When month is provided and year is not provided, rewards
   * for month of current year * is returned.When month is not provided and year is provided, total rewards of customer
   * for that year is returned.
   */
  @GetMapping("/customers/{customerId}/rewards")
  public ResponseEntity<Rewards> getRewardsForCustomer(@PathVariable Long customerId,
      @RequestParam(required = false) Integer month,
      @RequestParam(required = false) Integer year) {
    if (month != null) {
      return new ResponseEntity<>(rewardsService.getRewardsByYearMonth(Month.of(month),
          Year.of(Optional.ofNullable(year).orElseGet(() -> LocalDate.now().getYear())), customerId), HttpStatus.OK);
    } else if (year != null) {
      return new ResponseEntity<>(rewardsService.getRewardsByYear(Year.of(year), customerId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(rewardsService.getTotalRewards(customerId), HttpStatus.OK);
    }
  }

}
