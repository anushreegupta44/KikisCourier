package com.kikis.courier.service;

import com.kikis.courier.exception.VehicleNotFoundException;
import com.kikis.courier.model.Order;
import com.kikis.courier.model.Parcel;
import com.kikis.courier.model.Shipment;
import com.kikis.courier.model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryServiceTest {

  @InjectMocks
  private DeliveryService deliveryService;
  @Mock
  private VehicleService vehicleService;
  @Mock
  private ShipmentService shipmentService;

  @Test
  public void shouldDelieverOrder() {
    Order order = createOrder();
    when(shipmentService.buildShipment(order.getParcels())).thenReturn(new Shipment(List.of(order.getParcels().get(0)), 12.0, 12.9));
    Vehicle vehicle = new Vehicle(12.9, 112.9, 200.0);
    when(vehicleService.findEarliestAvailableVehicle()).thenReturn(vehicle);
    when(vehicleService.getMaxVehicleSpeed()).thenReturn(100.0);

    deliveryService.deliverOrder(order);

    assertEquals(true, order.getParcels().get(0).getIsDelivered());
    assertEquals(Double.valueOf(13.9), order.getParcels().get(0).getTimeToDeliver());
    assertEquals(Double.valueOf(38.7), vehicle.getAvailableInHours());
  }

  @Test(expected = VehicleNotFoundException.class)
  public void shouldThrowVehicleNotAvailableException() {
    Order order = createOrder();
    Vehicle vehicle = new Vehicle(12.9, 112.9, 200.0);
    when(vehicleService.getMaxVehicleSpeed()).thenReturn(null);

    deliveryService.deliverOrder(order);
  }

  @Test
  public void shouldGetInstance() {
    assertNotNull(DeliveryService.getInstance());
  }

  private Order createOrder() {
    Order order = new Order();
    Parcel parcel = new Parcel("PKG1", 12.0, 100.0, "coupon-1");
    List<Parcel> parcels = List.of(parcel);
    order.setParcels(parcels);
    order.setDeliveredParcels(new ArrayList<>());
    return order;
  }
}
