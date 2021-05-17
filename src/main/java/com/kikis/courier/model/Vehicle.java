package com.kikis.courier.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
  private Double availableInHours;
  private Double maxSpeed;
  private Double maxLoad;
}
