package com.kikis.courier.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CouponServiceTest {

  @InjectMocks
  private CouponService couponService;

  @Test
  public void shouldNotGetInvalidCouponFromCouponCode() {
    assertNull(couponService.getCouponFrom("invalid-coupon"));
  }

  @Test
  public void shouldGetCouponFromCouponCode() {
    assertNotNull(couponService.getCouponFrom("OFR001"));
  }
}
