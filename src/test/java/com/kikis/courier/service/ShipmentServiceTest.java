package com.kikis.courier.service;

import com.kikis.courier.model.Parcel;
import com.kikis.courier.model.Shipment;
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
public class ShipmentServiceTest {
  @InjectMocks
  private ShipmentService shipmentService;

  @Before
  public void init() {
    shipmentService.setMaxWeight(200.0);
  }

  @Test
  public void shouldReturnShipmentWithOneParcel() {
    List<Parcel> parcelList = new ArrayList<>();
    Parcel parcel = createParcel("parcelname", 10.0, 100.0, 11.9, false);
    parcelList.add(parcel);

    Shipment shipment = shipmentService.buildShipment(parcelList);

    assertEquals(1, shipment.getParcels().size());
    assertEquals(parcel, shipment.getParcels().get(0));
    assertEquals(Double.valueOf(10.0), shipment.getWeight());
    assertEquals(Double.valueOf(11.9), shipment.getDeliveryTime());
  }

  @Test
  public void shouldReturnNullShipmentIfAllPackagesAreDelivered() {
    List<Parcel> parcelList = new ArrayList<>();
    Parcel parcel = createParcel("parcelname", 10.0, 100.0, 11.9, true);
    parcelList.add(parcel);

    assertNull(shipmentService.buildShipment(parcelList));
  }

  @Test
  public void shouldReturnMaxNumberOfParcelShipment() {
    Parcel parcel1 = createParcel("parcel1", 10.0, 100.0, 11.9, false);
    Parcel parcel2 = createParcel("parcel2", 20.0, 100.0, 11.9, false);
    Parcel parcel3 = createParcel("parcel3", 200.0, 100.0, 11.9, false);
    List<Parcel> parcelList = new ArrayList<>(List.of(parcel1, parcel2, parcel3));

    Shipment shipment = shipmentService.buildShipment(parcelList);

    assertEquals(2, shipment.getParcels().size());
    assertEquals(List.of(parcel1, parcel2), shipment.getParcels());
    assertEquals(Double.valueOf(30.0), shipment.getWeight());
    assertEquals(Double.valueOf(11.9), shipment.getDeliveryTime());
  }

  @Test
  public void shouldReturnHeaviestShipment() {
    Parcel parcel1 = createParcel("parcel1", 10.0, 100.0, 11.9, false);
    Parcel parcel2 = createParcel("parcel2", 200.0, 100.0, 11.9, false);
    List<Parcel> parcelList = new ArrayList<>(List.of(parcel1, parcel2));

    Shipment shipment = shipmentService.buildShipment(parcelList);

    assertEquals(1, shipment.getParcels().size());
    assertEquals(List.of(parcel2), shipment.getParcels());
    assertEquals(Double.valueOf(200.0), shipment.getWeight());
    assertEquals(Double.valueOf(11.9), shipment.getDeliveryTime());
  }

  @Test
  public void shouldReturnTheShipmentWithFastestDelivery() {
    Parcel parcel1 = createParcel("parcel1", 175.0, 100.0, 11.9, false);
    Parcel parcel2 = createParcel("parcel2", 175.0, 100.0, 12.9, false);
    List<Parcel> parcelList = new ArrayList<>(List.of(parcel1, parcel2));

    Shipment shipment = shipmentService.buildShipment(parcelList);

    assertEquals(1, shipment.getParcels().size());
    assertEquals(List.of(parcel1), shipment.getParcels());
    assertEquals(Double.valueOf(175.0), shipment.getWeight());
    assertEquals(Double.valueOf(11.9), shipment.getDeliveryTime());
  }

  @Test
  public  void shouldGetShipmentServiceInstance(){
    assertNotNull(ShipmentService.getInstance());
  }

  private Parcel createParcel(String name, Double weight, Double distanceToDestination, Double timetoDelivery,
                              boolean isDelivered) {
    Parcel parcel = new Parcel(name, weight, distanceToDestination, "coupon-1");
    parcel.setTimeToDeliver(timetoDelivery);
    parcel.setIsDelivered(isDelivered);
    return parcel;
  }
}
