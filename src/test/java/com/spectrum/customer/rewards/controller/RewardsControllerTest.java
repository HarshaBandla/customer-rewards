package com.spectrum.customer.rewards.controller;

import com.spectrum.customer.rewards.model.Rewards;
import com.spectrum.customer.rewards.service.RewardsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Month;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RewardsControllerTest {

    @Mock
    private RewardsService rewardsService;

    @InjectMocks
    private RewardsController rewardsController;

    @Test
    void testGetRewardsForCustomerWithMonthAndYear() {

        Long customerId = 1L;
        Integer month = 2;
        Integer year = 2022;
        double rewardValue = 100.0;
        Rewards rewards = new Rewards(customerId, rewardValue);
        when(rewardsService.getRewardsByYearMonth(Month.FEBRUARY, Year.of(2022), customerId)).thenReturn(rewards);

        EntityModel<Rewards> response = rewardsController.getRewardsForCustomer(customerId, month, year);

        assertEquals(rewards, response.getContent());
        verify(rewardsService).getRewardsByYearMonth(Month.FEBRUARY, Year.of(2022), customerId);
    }

    @Test
    void testGetRewardsForCustomerWithYear() {

        Long customerId = 1L;
        Integer year = 2022;
        double rewardValue = 120.0;
        Rewards rewards = new Rewards(customerId, rewardValue);
        when(rewardsService.getRewardsByYear(Year.of(2022), customerId)).thenReturn(rewards);

        EntityModel<Rewards> response = rewardsController.getRewardsForCustomer(customerId, null, year);

        assertEquals(rewards, response.getContent());
        verify(rewardsService).getRewardsByYear(Year.of(2022), customerId);
    }

    @Test
    void testGetRewardsForCustomerWithoutMonthOrYear() {

        Long customerId = 1L;
        double rewardValue = 120.0;
        Rewards rewards = new Rewards(customerId, rewardValue);
        when(rewardsService.getTotalRewards(customerId)).thenReturn(rewards);

        EntityModel<Rewards> response = rewardsController.getRewardsForCustomer(customerId, null, null);

        assertEquals(rewards, response.getContent());
        verify(rewardsService).getTotalRewards(customerId);
    }
}
