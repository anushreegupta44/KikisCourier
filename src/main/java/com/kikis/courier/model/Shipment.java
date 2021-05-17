package com.kikis.courier.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Shipment {
  private List<Parcel> parcels;
  private Double weight;
  private Double deliveryTime;
}
