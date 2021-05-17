package com.kikis.courier;

import com.kikis.courier.model.Order;
import com.kikis.courier.service.DeliveryService;
import com.kikis.courier.service.PricingService;
import lombok.Getter;

@Getter
public class KikisCourierService {
  public static KikisCourierService INSTANCE = null;

  private final PricingService pricingService;
  private final DeliveryService deliveryService;

  private KikisCourierService(PricingService pricingService, DeliveryService deliveryService) {
    this.pricingService = pricingService;
    this.deliveryService = deliveryService;
  }

  public void calculatePrice(Order order) {
    pricingService.calculatePriceForOrder(order);
  }

  public void calculateEstimatedDeliveryTimeFor(Order order) {
    deliveryService.deliverOrder(order);
  }

  public static KikisCourierService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new KikisCourierService(PricingService.getInstance(), DeliveryService.getInstance());
    }
    return INSTANCE;
  }
}
