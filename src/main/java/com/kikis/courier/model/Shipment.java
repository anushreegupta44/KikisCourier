package com.kikis.courier.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Shipment {
  //TODO: make private
  List<Parcel> parcels;
  Double weight;
  Double deliveryTime;

  public Shipment(List<Parcel> parcels, Double weight) {
    this.parcels = parcels;
    this.weight = weight;
  }
}
