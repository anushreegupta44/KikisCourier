package com.kikis.courier;

import com.kikis.courier.domain.Order;
import com.kikis.courier.service.PricingService;

public class KikisCourierService {

  private final PricingService pricingService;

  public KikisCourierService(PricingService pricingService) {
    this.pricingService = pricingService;
  }

  public void calculatePrice(Order order) {
    pricingService.calculatePriceForOrder(order);
  }
}
