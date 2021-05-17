package com.kikis.courier.service;

import com.kikis.courier.model.Vehicle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleService {
  public static VehicleService INSTANCE = null;

  private List<Vehicle> vehicles;

  public Vehicle findEarliestAvailableVehicle() {
    return vehicles.stream().min((v1, v2) -> (int) (v1.getAvailableInHours() - v2.getAvailableInHours())).orElse(null);
  }

  public Double getMaxVehicleSpeed() {
    Optional<Vehicle> optionalVehicle = vehicles.stream().findAny();
    return optionalVehicle.map(Vehicle::getMaxSpeed).orElse(null);
  }

  public static VehicleService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new VehicleService();
    }
    return INSTANCE;
  }
}
