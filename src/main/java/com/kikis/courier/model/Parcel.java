package com.kikis.courier.model;

import lombok.Data;

import static java.util.Objects.isNull;

@Data
public class Parcel {
  private String id;
  private Double weight;
  private Double distanceToDestination;
  private String couponCode;
  private Double costToDeliver;
  private Double totalDiscount;
  private Double timeToDeliver;
  private Boolean isDelivered;

  public Parcel(String id, Double weight, Double distanceToDestination, String couponCode) {
    this.id = id;
    this.weight = weight;
    this.distanceToDestination = distanceToDestination;
    this.couponCode = couponCode;
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    if (!isNull(id)) {
      res.append(id).append(" ");
    }
    if (!isNull(totalDiscount)) {
      res.append(totalDiscount).append(" ");
    }
    if (!isNull(costToDeliver)) {
      res.append(costToDeliver).append(" ");
    }
    if (!isNull(timeToDeliver)) {
      res.append(timeToDeliver).append(" ");
    }
    return res.toString();
  }
}
