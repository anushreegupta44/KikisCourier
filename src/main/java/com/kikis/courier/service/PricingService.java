package com.kikis.courier.service;

import com.kikis.courier.domain.Order;
import com.kikis.courier.domain.Parcel;
import com.kikis.courier.util.Printer;

public class PricingService {
  private final DiscountService discountService;
  private final Printer printer;
  public static final double WEIGHT_COST_MULTIPLIER = 10.0;
  public static final double DISTANCE_COST_MULTIPLER = 5.0;

  public PricingService(DiscountService discountService, Printer printer) {
    this.discountService = discountService;
    this.printer = printer;
  }

  public void calculatePriceForOrder(Order order) {
    for (Parcel parcel : order.getParcels()) {
      Double costPrice = calculateBaseCostForParcel(order.getBaseDeliveryCost(), parcel);
      Double discount = discountService.getApplicableDiscount(parcel, costPrice);
      parcel.setCostToDeliver(costPrice - discount);
      parcel.setTotalDiscount(discount);
      printer.printToConsole(parcel.toString());
    }
  }


  private Double calculateBaseCostForParcel(Double baseDeliveryCost, Parcel parcel) {
    return baseDeliveryCost + (parcel.getWeight() * WEIGHT_COST_MULTIPLIER) + (parcel.getDistanceToDestination() * DISTANCE_COST_MULTIPLER);
  }
}
