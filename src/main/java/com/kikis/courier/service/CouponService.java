package com.kikis.courier.service;

import com.kikis.courier.domain.Coupon;

import java.util.Map;

import static java.util.Objects.isNull;

public class CouponService {
  private final Map<String, Coupon> coupons;

  public CouponService() {
    Coupon offer1Coupon = new Coupon("OFR001", 0, 199, 70, 200, 10.0);
    Coupon offer2Coupon = new Coupon("OFR002", 50, 150, 100, 250, 7.0);
    Coupon offer3Coupon = new Coupon("OFR003", 50, 250, 10, 150, 5.0);
    this.coupons = Map.of(offer1Coupon.getCode(), offer1Coupon, offer2Coupon.getCode(), offer2Coupon, offer3Coupon.getCode(), offer3Coupon);
  }

  public Coupon getCouponFrom(String couponCode) {
    if (isNull(couponCode)) {
      return null;
    }
    Coupon coupon = coupons.get(couponCode);
    if (isNull(coupon)) {
      return null;
    }
    return coupon;
  }
}
