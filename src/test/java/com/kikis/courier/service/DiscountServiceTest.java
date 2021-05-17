package com.kikis.courier.service;

import com.kikis.courier.model.Coupon;
import com.kikis.courier.model.Parcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {
  @InjectMocks
  private DiscountService discountService;
  @Mock
  private CouponService couponService;

  @Test
  public void shouldGetApplicableDiscountWhenCouponCodeInParcelIsValid() {
    Parcel parcel = new Parcel("Parcel-1", 66.0, 55.0, "valid-coupon-1");
    Coupon coupon = new Coupon("valid-coupon-1", 10, 100, 5, 70, 10.0);
    when(couponService.getCouponFrom("valid-coupon-1")).thenReturn(coupon);

    assertEquals(Double.valueOf(9.8), discountService.getApplicableDiscount(parcel, 98.0));
  }

  @Test
  public void shouldReturnZeroDiscountIfCouponCodeNotFound() {
    Parcel parcel = new Parcel("Parcel-1", 75.0, 55.0, "OFR0099");
    when(couponService.getCouponFrom("OFR0099")).thenReturn(null);

    assertEquals(Double.valueOf(0.0), discountService.getApplicableDiscount(parcel, 98.0));
  }

  @Test
  public void shouldReturnZeroIfParcelIsWithoutDiscountCode() {
    Parcel parcel = new Parcel("Parcel-1", 75.0, 55.0, null);

    assertEquals(Double.valueOf(0.0), discountService.getApplicableDiscount(parcel, 98.0));
  }

  @Test
  public void shouldGetInstance() {
    assertNotNull(DiscountService.getInstance());
  }
}
