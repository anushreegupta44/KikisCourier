package com.kikis.courier.domain.coupon;

public class OFR003 implements Coupon {
  private final String code;
  private final Integer minDistance;
  private final Integer maxDistance;
  private final Integer minWeight;
  private final Integer maxWeight;
  private final Double percentDiscount;

  public OFR003() {
    this.code = "OFR003";
    this.minDistance = 50;
    this.maxDistance = 250;
    this.minWeight = 10;
    this.maxWeight = 150;
    this.percentDiscount = 5.0;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public Integer getMinDistance() {
    return this.minDistance;
  }

  @Override
  public Integer getMaxDistance() {
    return this.maxDistance;
  }

  @Override
  public Integer getMinWeight() {
    return this.minWeight;
  }

  @Override
  public Integer getMaxWeight() {
    return this.maxWeight;
  }

  @Override
  public Double getPercentDiscount() {
    return this.percentDiscount;
  }
}
