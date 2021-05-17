package com.kikis.courier.service;

import com.kikis.courier.model.Order;
import com.kikis.courier.model.Parcel;

public class PricingService {
  public static PricingService INSTANCE = null;

  private final DiscountService discountService;
  public static final double WEIGHT_COST_MULTIPLIER = 10.0;
  public static final double DISTANCE_COST_MULTIPLER = 5.0;

  private PricingService(DiscountService discountService) {
    this.discountService = discountService;
  }

  public void calculatePriceForOrder(Order order) {
    for (Parcel parcel : order.getParcels()) {
      Double basePrice = calculateTotalCostForParcel(order.getBaseDeliveryCost(), parcel);
      Double discount = discountService.getApplicableDiscount(parcel, basePrice);
      parcel.setCostToDeliver(basePrice - discount);
      parcel.setTotalDiscount(discount);
    }
  }

  private Double calculateTotalCostForParcel(Double baseDeliveryCost, Parcel parcel) {
    return baseDeliveryCost + (parcel.getWeight() * WEIGHT_COST_MULTIPLIER) + (parcel.getDistanceToDestination() * DISTANCE_COST_MULTIPLER);
  }

  public static PricingService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new PricingService(DiscountService.getInstance());
    }
    return INSTANCE;
  }
}
