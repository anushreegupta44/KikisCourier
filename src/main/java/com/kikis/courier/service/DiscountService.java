package com.kikis.courier.service;

import com.kikis.courier.model.Coupon;
import com.kikis.courier.model.Parcel;

import static java.util.Objects.isNull;

public class DiscountService {
  public static DiscountService INSTANCE = null;

  private final CouponService couponService;

  private DiscountService(CouponService couponService) {
    this.couponService = couponService;
  }

  //TODO: remove full parcel object
  public Double getApplicableDiscount(Parcel parcel, Double parcelCost) {
    Coupon coupon = couponService.getCouponFrom(parcel.getCouponCode());
    if (isNull(coupon)) {
      return 0.0;
    }
    return coupon.getFinalDiscount(parcel.getDistanceToDestination(), parcel.getWeight(), parcelCost);
  }

  public static DiscountService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new DiscountService(CouponService.getInstance());
    }
    return INSTANCE;
  }
}
