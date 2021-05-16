package com.kikis.courier.service;

import com.kikis.courier.domain.Coupon;
import com.kikis.courier.domain.Parcel;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class DiscountService {

  private final Map<String, Coupon> validCoupons = new HashMap<>();

  public DiscountService() {
    validCoupons.put("OFR001", new Coupon("OFR001", 1, 199,
            70, 200, 10.0));
    validCoupons.put("OFR002", new Coupon("OFR002", 50, 150,
            100, 250, 7.0));
    validCoupons.put("OFR003", new Coupon("OFR001", 50, 250,
            10, 150, 5.0));

  }

  public Double getApplicableDiscount(Parcel parcel, Double parcelCost) {
    String couponCode = parcel.getCouponCode();
    if (isNull(couponCode)) {
      return 0.0;
    }
    Coupon coupon = validCoupons.get(couponCode.trim());
    if (isNull(coupon)) {
      return 0.0;
    }
    return coupon.calculateDiscount(parcel.getWeight(), parcel.getDistanceToDestination(), parcelCost);
  }
}
