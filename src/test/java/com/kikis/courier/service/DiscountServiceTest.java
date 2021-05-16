package com.kikis.courier.service;

import com.kikis.courier.domain.Parcel;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiscountServiceTest {
  private static DiscountService discountService;

  @BeforeClass
  public static void setUp() {
    discountService = new DiscountService();
  }

  @Test
  public void shouldGetApplicableDiscountWhenCouponCodeInParcelIsValid() {
    Parcel parcel = new Parcel("Parcel-1", 75.0, 55.0, "OFR001");
    assertEquals(Double.valueOf(9.8), discountService.getApplicableDiscount(parcel, 98.0));
  }

  @Test
  public void shouldReturnZeroDiscountIfCoouponCodeNotFound() {
    Parcel parcel = new Parcel("Parcel-1", 75.0, 55.0, "OFR0099");
    assertEquals(Double.valueOf(0.0), discountService.getApplicableDiscount(parcel, 98.0));
  }

  @Test
  public void shouldReturnZeroIfParcelIsWithoutDiscountCode() {
    Parcel parcel = new Parcel("Parcel-1", 75.0, 55.0, null);
    assertEquals(Double.valueOf(0.0), discountService.getApplicableDiscount(parcel, 98.0));
  }
}
