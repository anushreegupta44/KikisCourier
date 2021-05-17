package com.kikis.courier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private Double baseDeliveryCost;
  private List<Parcel> parcels;
  private List<Parcel> deliveredParcels;
}
