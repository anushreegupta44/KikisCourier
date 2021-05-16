package com.kikis.courier.domain;

import lombok.Data;

@Data
public class Parcel {
  private String id;
  private Double weight;
  private Double distanceToDestination;
  private String couponCode;
  private Double costToDeliver;
  private Double totalDiscount;

  public Parcel(String id, Double weight, Double distanceToDestination, String couponCode) {
    this.id = id;
    this.weight = weight;
    this.distanceToDestination = distanceToDestination;
    this.couponCode = couponCode;
  }
}
