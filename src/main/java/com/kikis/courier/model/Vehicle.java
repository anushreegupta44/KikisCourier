package com.kikis.courier.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle implements Comparable<Vehicle> {
  Double availableInHours;
  Double maxSpeed;
  Double maxLoad;

  @Override
  public int compareTo(Vehicle o) {
    return (int) (this.availableInHours - o.availableInHours);
  }
}
