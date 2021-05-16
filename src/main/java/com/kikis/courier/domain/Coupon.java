package com.kikis.courier.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coupon {
  private String code;
  private Integer minDistance;
  private Integer maxDistance;
  private Integer minWeight;
  private Integer maxWeight;
  private Double percentDiscount;

  public Double calculateDiscount(Double weight, Double distance, Double sellingPrice) {
    if (weight >= minWeight && weight <= maxWeight
                && distance >= minDistance && distance <= maxDistance) {
      return (percentDiscount * sellingPrice) / 100;
    }
    return 0.0;
  }
}
