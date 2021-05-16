package com.kikis.courier;

import com.kikis.courier.domain.coupon.Coupon;
import com.kikis.courier.domain.coupon.OFR001;
import com.kikis.courier.domain.coupon.OFR002;
import com.kikis.courier.domain.coupon.OFR003;
import com.kikis.courier.service.CouponService;
import com.kikis.courier.service.DiscountService;
import com.kikis.courier.service.PricingService;
import com.kikis.courier.util.Printer;

import java.util.ArrayList;
import java.util.List;

public class Runner {
  public static void start() {
    KikisCourierService kikisCourierService = init();
    kikisCourierService.start();
  }

  private static KikisCourierService init() {
    List<Coupon> coupons = new ArrayList<>();
    coupons.add(new OFR001());
    coupons.add(new OFR002());
    coupons.add(new OFR003());
    CouponService couponService = new CouponService(coupons);
    DiscountService discountService = new DiscountService(couponService);
    Printer printer = new Printer();
    PricingService pricingService = new PricingService(discountService, printer);
    return new KikisCourierService(pricingService);
  }
}
