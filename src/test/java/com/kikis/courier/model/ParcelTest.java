package com.kikis.courier.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParcelTest {

  @Test
  public void shouldPrintParcelInCorrectFormat() {
    Parcel parcel = new Parcel("PKG1", 22.2, 100.99, "Coupon-1");
    parcel.setCostToDeliver(980.33);
    parcel.setIsDelivered(true);
    parcel.setTimeToDeliver(2.99);
    assertEquals("PKG1 980.33 2.99", parcel.toString());
  }
}
