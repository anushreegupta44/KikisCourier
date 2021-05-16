package com.kikis.courier.domain.coupon;

public class OFR001 implements Coupon {
  private final String code;
  private final Integer minDistance;
  private final Integer maxDistance;
  private final Integer minWeight;
  private final Integer maxWeight;
  private final Double percentDiscount;

  public OFR001() {
    this.code = "OFR001";
    this.minDistance = 1;
    this.maxDistance = 199;
    this.minWeight = 70;
    this.maxWeight = 200;
    this.percentDiscount = 10.0;
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
