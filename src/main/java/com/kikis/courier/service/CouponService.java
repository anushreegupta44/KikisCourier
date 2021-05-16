package com.kikis.courier.service;

import com.kikis.courier.domain.coupon.Coupon;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class CouponService {
  private final Map<String, Coupon> coupons;

  public CouponService(List<Coupon> coupons) {
    this.coupons = coupons.stream().collect(Collectors.toMap(Coupon::getCode, Function.identity()));
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
