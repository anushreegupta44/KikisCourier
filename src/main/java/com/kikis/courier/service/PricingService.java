package com.kikis.courier.service;

import com.kikis.courier.domain.Order;
import com.kikis.courier.domain.Parcel;

public class PricingService {
  private final DiscountService discountService;
  public static final double WEIGHT_COST_MULTIPLIER = 10.0;
  public static final double DISTANCE_COST_MULTIPLER = 5.0;

  public PricingService(DiscountService discountService) {
    this.discountService = discountService;
  }

  public void calculatePriceForOrder(Order order) {
    for (Parcel parcel : order.getParcels()) {
      Double costPrice = calculateBaseCostForParcel(order.getBaseDeliveryCost(), parcel);
      Double discount = discountService.getApplicableDiscount(parcel, costPrice);
      parcel.setCostToDeliver(costPrice - discount);
      parcel.setTotalDiscount(discount);
      printParcelInfo(parcel);
    }
  }

  private void printParcelInfo(Parcel parcel) {
    System.out.print(parcel.getId() + " ");
    System.out.print(parcel.getTotalDiscount() + " ");
    System.out.println(parcel.getCostToDeliver());
  }

  private Double calculateBaseCostForParcel(Double baseDeliveryCost, Parcel parcel) {
    return baseDeliveryCost + (parcel.getWeight() * WEIGHT_COST_MULTIPLIER) + (parcel.getDistanceToDestination() * DISTANCE_COST_MULTIPLER);
  }
}
