package com.kikis.courier.service;

import com.kikis.courier.exception.VehicleNotFoundException;
import com.kikis.courier.model.Order;
import com.kikis.courier.model.Parcel;
import com.kikis.courier.model.Shipment;
import com.kikis.courier.model.Vehicle;
import lombok.Getter;

import java.util.List;

import static java.util.Objects.isNull;

@Getter
public class DeliveryService {
  public static DeliveryService INSTANCE = null;

  private final ShipmentService shipmentService;
  private final VehicleService vehicleService;
  public static final String VEHICLE_MAX_SPEED_UNKNOWN_MESSAGE = "Vehicle max speed unknown.";
  public static final String VEHICLE_UNAVAILABLE_MESSAGE = "Vehicle unavailable.";

  private DeliveryService(ShipmentService shipmentService, VehicleService vehicleService) {
    this.shipmentService = shipmentService;
    this.vehicleService = vehicleService;
  }

  public void deliverOrder(Order order) {
    setBaseDeliveryTime(order.getParcels());
    makeShipmentsAndDeliver(order);
  }

  private void setBaseDeliveryTime(List<Parcel> parcels) {
    Double maxVehicleSpeed = vehicleService.getMaxVehicleSpeed();
    if (isNull(maxVehicleSpeed)) {
      throw new VehicleNotFoundException(VEHICLE_MAX_SPEED_UNKNOWN_MESSAGE);
    }
    for (Parcel parcel : parcels) {
      parcel.setTimeToDeliver(parcel.getDistanceToDestination() / maxVehicleSpeed);
    }
  }

  private void makeShipmentsAndDeliver(Order order) {
    while (order.getDeliveredParcels().size() < order.getParcels().size()) {
      Shipment nextShipment = shipmentService.buildShipment(order.getParcels());
      deliverShipment(nextShipment);
      order.getDeliveredParcels().addAll(nextShipment.getParcels());
    }
  }

  private void deliverShipment(Shipment nextShipment) {
    Vehicle vehicle = vehicleService.findEarliestAvailableVehicle();
    deliverShipmentAndCalculateWaitTime(nextShipment, vehicle);
  }

  private void deliverShipmentAndCalculateWaitTime(Shipment nextShipment, Vehicle vehicle) {
    Double waitTime = vehicle.getAvailableInHours();
    for (Parcel parcel : nextShipment.getParcels()) {
      parcel.setTimeToDeliver(parcel.getTimeToDeliver() + waitTime);
      parcel.setIsDelivered(true);
    }
    vehicle.setAvailableInHours((2 * nextShipment.getDeliveryTime()) + waitTime);
  }

  public static DeliveryService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new DeliveryService(ShipmentService.getInstance(), VehicleService.getInstance());
    }
    return INSTANCE;
  }
}
