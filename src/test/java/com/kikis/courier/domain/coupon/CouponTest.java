package com.kikis.courier.domain.coupon;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CouponTest {
  private static Coupon coupon;

  @BeforeClass
  public static void init() {
    coupon = new Coupon() {
      @Override
      public String getCode() {
        return "valid-coupon-code";
      }

      @Override
      public Integer getMinDistance() {
        return 5;
      }

      @Override
      public Integer getMaxDistance() {
        return 10;
      }

      @Override
      public Integer getMinWeight() {
        return 50;
      }

      @Override
      public Integer getMaxWeight() {
        return 100;
      }

      @Override
      public Double getPercentDiscount() {
        return 10.0;
      }
    };
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
