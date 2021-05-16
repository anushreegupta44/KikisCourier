package com.kikis.courier.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CouponTest {
  private static Coupon coupon;

  @BeforeClass
  public static void init() {
    coupon = new Coupon("coupon-1", 12, 20, 55, 75, 10.0);
  }

  @Test
  public void shouldCalculateCorrectDiscountWhenWeightAndDistanceAreWithinBounds() {
    assertEquals(Double.valueOf(2.2), coupon.calculateDiscount(65.0, 18.0, 22.0));
  }

  @Test
  public void shouldNotCalculateDiscountWhenWeightIsLesserThanLowerBound() {
    assertEquals(Double.valueOf(0.0), coupon.calculateDiscount(54.0, 18.0, 22.0));
  }

  @Test
  public void shouldCalculateDiscountWhenWeightIsEqualToLowerBound() {
    assertEquals(Double.valueOf(2.2), coupon.calculateDiscount(55.0, 18.0, 22.0));
  }

  @Test
  public void shouldNotCalculateDiscountWhenWeightIsGreaterThanUpperBound() {
    assertEquals(Double.valueOf(0.0), coupon.calculateDiscount(76.0, 18.0, 22.0));
  }

  @Test
  public void shouldCalculateDiscountWhenWeightIsEqualToUpperBound() {
    assertEquals(Double.valueOf(2.2), coupon.calculateDiscount(75.0, 18.0, 22.0));
  }

  @Test
  public void shouldNotCalculateDiscountWhenDistanceIsLesserThanLowerBound() {
    assertEquals(Double.valueOf(0.0), coupon.calculateDiscount(54.0, 11.0, 22.0));
  }

  @Test
  public void shouldCalculateDiscountWhenDistanceIsEqualToLowerBound() {
    assertEquals(Double.valueOf(2.2), coupon.calculateDiscount(65.0, 12.0, 22.0));
  }

  @Test
  public void shouldNotCalculateDiscountWhenDistanceIsGreaterThanUpperBound() {
    assertEquals(Double.valueOf(0.0), coupon.calculateDiscount(76.0, 21.0, 22.0));
  }

  @Test
  public void shouldCalculateDiscountWhenDistanceIsEqualToEqualBound() {
    assertEquals(Double.valueOf(2.2), coupon.calculateDiscount(65.0, 20.0, 22.0));
  }
}
