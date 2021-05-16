package com.kikis.courier.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coupon {
  private final String code;
  private final Integer minDistance;
  private final Integer maxDistance;
  private final Integer minWeight;
  private final Integer maxWeight;
  private final Double percentDiscount;

  public Double getFinalDiscount(Double distance, Double weight, Double sellingPrice) {
    if (distance >= minDistance && distance <= maxDistance && weight >= minWeight && weight <= maxWeight) {
      return (percentDiscount * sellingPrice) / 100;
    }
    return 0.0;
  }
}
