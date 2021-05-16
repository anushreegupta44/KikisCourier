package com.kikis.courier.service;

import com.kikis.courier.domain.Parcel;
import com.kikis.courier.domain.Coupon;

import static java.util.Objects.isNull;

public class DiscountService {

  private final CouponService couponService;

  public DiscountService(CouponService couponService) {
    this.couponService = couponService;
  }

  public Double getApplicableDiscount(Parcel parcel, Double parcelCost) {
    Coupon coupon = couponService.getCouponFrom(parcel.getCouponCode());
    if (isNull(coupon)) {
      return 0.0;
    }
    return coupon.getFinalDiscount(parcel.getDistanceToDestination(), parcel.getWeight(), parcelCost);
  }
}
