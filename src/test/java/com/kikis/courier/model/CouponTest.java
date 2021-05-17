package com.kikis.courier.model;

import com.kikis.courier.model.Coupon;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CouponTest {
  private static Coupon coupon;

  @BeforeClass
  public static void init() {
    coupon = new Coupon("valid-coupon-code", 5, 10, 50, 100, 10.0);
  }

  @Test
  public void shouldCalculateCorrectDiscountWhenWeightAndDistanceAreWithinBounds() {
    assertEquals(Double.valueOf(2.2), coupon.getFinalDiscount(8.0, 55.0, 22.0));
  }

  @Test
  public void shouldNotCalculateDiscountWhenWeightIsLesserThanLowerBound() {
    assertEquals(Double.valueOf(0.0), coupon.getFinalDiscount(8.0, 49.0, 22.0));
  }

  @Test
  public void shouldCalculateDiscountWhenWeightIsEqualToLowerBound() {
    assertEquals(Double.valueOf(2.2), coupon.getFinalDiscount(8.0, 50.0, 22.0));
  }

  @Test
  public void shouldNotCalculateDiscountWhenWeightIsGreaterThanUpperBound() {
    assertEquals(Double.valueOf(0.0), coupon.getFinalDiscount(8.0, 101.0, 22.0));
  }

  @Test
  public void shouldCalculateDiscountWhenWeightIsEqualToUpperBound() {
    assertEquals(Double.valueOf(2.2), coupon.getFinalDiscount(8.0, 100.0, 22.0));
  }

  @Test
  public void shouldNotCalculateDiscountWhenDistanceIsLesserThanLowerBound() {
    assertEquals(Double.valueOf(0.0), coupon.getFinalDiscount(4.0, 54.0, 22.0));
  }

  @Test
  public void shouldCalculateDiscountWhenDistanceIsEqualToLowerBound() {
    assertEquals(Double.valueOf(2.2), coupon.getFinalDiscount(5.0, 65.0, 22.0));
  }

  @Test
  public void shouldNotCalculateDiscountWhenDistanceIsGreaterThanUpperBound() {
    assertEquals(Double.valueOf(0.0), coupon.getFinalDiscount(11.0, 76.0, 22.0));
  }

  @Test
  public void shouldCalculateDiscountWhenDistanceIsEqualToEqualBound() {
    assertEquals(Double.valueOf(2.2), coupon.getFinalDiscount(10.0, 65.0, 22.0));
  }
}
