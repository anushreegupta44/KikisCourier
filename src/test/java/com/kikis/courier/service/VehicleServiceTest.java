package com.kikis.courier.service;

import com.kikis.courier.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {
  @InjectMocks
  private VehicleService vehicleService;

  @Before
  public void before() {
    List<Vehicle> vehicles = new ArrayList<>();
    vehicles.add(new Vehicle(12.22, 100.9, 120.0));
    vehicles.add(new Vehicle(12.00, 100.9, 120.0));
    vehicleService.setVehicles(vehicles);
  }

  @Test
  public void shouldFindEarliestAvailableVehicle() {
    Vehicle earliestAvailableVehicle = vehicleService.findEarliestAvailableVehicle();

    assertEquals(Double.valueOf(12.22), earliestAvailableVehicle.getAvailableInHours());
  }

  @Test
  public void shouldGetMaxVehicleSpeed() {
    assertEquals(Double.valueOf(100.9), vehicleService.getMaxVehicleSpeed());
  }

  @Test
  public void shouldReturnNullWhenNoVehicleGetMaxVehicleSpeed() {
    vehicleService.setVehicles(List.of());
    assertNull(vehicleService.getMaxVehicleSpeed());
  }

  @Test
  public void shouldGetInstanceOfVehicleService() {
    assertNotNull(VehicleService.getInstance());
  }
}
