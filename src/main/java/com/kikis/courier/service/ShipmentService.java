package com.kikis.courier.service;

import com.kikis.courier.model.Parcel;
import com.kikis.courier.model.Shipment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShipmentService {
  public static ShipmentService INSTANCE = null;

  private Double maxWeight;

  public Shipment buildShipment(List<Parcel> parcels) {
    List<Parcel> undeliveredParcels = findUndeliveredParcels(parcels);
    if (isNull(undeliveredParcels) || undeliveredParcels.size() == 0) {
      return null;
    }
    Shipment maxPackageShipment = findMaxPackageShipment(undeliveredParcels);
    if (!isNull(maxPackageShipment)) {
      return maxPackageShipment;
    }
    Shipment heaviestShipment = findHeaviestShipment(undeliveredParcels);
    if (!isNull(heaviestShipment)) {
      return heaviestShipment;
    }
    Shipment earliestDeliverableShipment = earliestDeliverableShipment(undeliveredParcels);
    if (!isNull(earliestDeliverableShipment)) {
      return earliestDeliverableShipment;
    }
    return null;
  }

  private List<Parcel> findUndeliveredParcels(List<Parcel> parcels) {
    return parcels.stream().filter(parcel -> parcel.getIsDelivered() == null || !parcel.getIsDelivered()).collect(Collectors.toList());
  }

  private Shipment earliestDeliverableShipment(List<Parcel> parcels) {
    parcels.sort(Comparator.comparingDouble(Parcel::getTimeToDeliver));
    List<Shipment> shipments = findPossibleShipments(parcels);
    shipments.sort(Comparator.comparingDouble(Shipment::getDeliveryTime));
    if (shipments.size() == 1) {
      return shipments.get(0);
    }
    if (shipments.size() > 1 && shipments.get(0).getDeliveryTime() < shipments.get(1).getDeliveryTime()) {
      return shipments.get(0);
    }
    return null;
  }

  private Shipment findHeaviestShipment(List<Parcel> parcels) {
    parcels.sort((p1, p2) -> Double.compare(p2.getWeight(), p1.getWeight()));
    List<Shipment> shipments = findPossibleShipments(parcels);
    shipments.sort((s1, s2) -> Double.compare(s2.getWeight(), s1.getWeight()));
    if (shipments.size() == 1) {
      return shipments.get(0);
    }
    if (shipments.size() > 1 && shipments.get(0).getWeight() > shipments.get(1).getWeight()) {
      return shipments.get(0);
    }
    return null;
  }

  private Shipment findMaxPackageShipment(List<Parcel> parcels) {
    parcels.sort(Comparator.comparingDouble(Parcel::getWeight));
    List<Shipment> shipments = findPossibleShipments(parcels);
    shipments.sort((sl1, sl2) -> sl2.getParcels().size() - sl1.getParcels().size());
    if (shipments.size() == 1) {
      return shipments.get(0);
    }
    //TODO: extract  condition to function
    if (shipmentsMoreThanOneAndMaxParcelSizeIsUnique(shipments)) {
      return shipments.get(0);
    }
    return null;
  }

  private boolean shipmentsMoreThanOneAndMaxParcelSizeIsUnique(List<Shipment> shipments) {
    return shipments.size() > 1 && shipments.get(0).getParcels().size() != shipments.get(1).getParcels().size();
  }

  private List<Shipment> findPossibleShipments(List<Parcel> parcels) {
    List<Shipment> shipments = new ArrayList<>();
    double currWeight = 0;
    double timeToDeliver = 0;
    List<Parcel> parcelList = new ArrayList<>();
    for (Parcel parcel : parcels) {
      if (currWeight + parcel.getWeight() <= maxWeight) {
        parcelList.add(parcel);
        currWeight += parcel.getWeight();
      } else {
        shipments.add(new Shipment(parcelList, currWeight, timeToDeliver));
        parcelList = new ArrayList<>();
        parcelList.add(parcel);
        currWeight = parcel.getWeight();
        timeToDeliver = parcel.getTimeToDeliver();
      }
      timeToDeliver = Math.max(timeToDeliver, parcel.getTimeToDeliver());
    }
    if (!parcelList.isEmpty()) {
      shipments.add(new Shipment(parcelList, currWeight, timeToDeliver));
    }
    return shipments;
  }

  public static ShipmentService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ShipmentService();
    }
    return INSTANCE;
  }
}
