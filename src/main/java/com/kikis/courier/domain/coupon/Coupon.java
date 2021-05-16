package com.kikis.courier.domain.coupon;

public interface Coupon {

  default Double getFinalDiscount(Double distance, Double weight, Double sellingPrice) {
    if (distance >= getMinDistance() && distance <= getMaxDistance()
                && weight >= getMinWeight() && weight <= getMaxWeight()) {
      return (getPercentDiscount() * sellingPrice) / 100;
    }
    return 0.0;
  }

  String getCode();

  Integer getMinDistance();

  Integer getMaxDistance();

  Integer getMinWeight();

  Integer getMaxWeight();

  Double getPercentDiscount();
}
