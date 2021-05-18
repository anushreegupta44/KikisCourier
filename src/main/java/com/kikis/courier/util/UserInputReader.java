package com.kikis.courier.util;

import com.kikis.courier.model.Order;
import com.kikis.courier.model.Parcel;
import com.kikis.courier.model.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.kikis.courier.util.Printer.printToConsole;

public class UserInputReader {
  private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  private static final String ALL_SPACE_REGEX = "[ \\t]+";
  private static final String BASE_DELIVERY_AND_NUMBER_OF_PACKAGES = "Please enter base delivery cost for order and number of packages separated by a space";
  private static final String ERROR_MESSAGE_BASE_DELIVERY_COST_OR_PARCEL_NUMBER = "Some error entering base delivery cost or number of parcel info. Please enter again";
  private static final String ERROR_MESSAGE_PARCEL = "Some error entering parcel info. Please enter again";
  private static final String ERROR_MESSAGE_VEHICLE = "Some error entering vehicle info. Please enter again";
  public static final String PARCEL_INFO_MESSAGE = "Please enter Parcel name, weight, distance to destination, coupon" +
                                                           " code separated by space";
  public static final String VEHICLE_INFO = "Please enter vehicle number, max speed and max load separated by space";

  public static int takeUserOption() throws IOException {
    String userOption = readLine();
    return Integer.parseInt(userOption.trim());
  }

  public static Order getOrderFromInput() throws IOException {
    printToConsole(BASE_DELIVERY_AND_NUMBER_OF_PACKAGES);
    return initialiseOrderFromUserInput();
  }

  public static Order initialiseOrderFromUserInput() {
    Order order = new Order();
    try {
      String baseDeliveryCostAndParcelNumber = readLine();
      String[] orderAndParcelNumber = baseDeliveryCostAndParcelNumber.split(ALL_SPACE_REGEX);
      order.setBaseDeliveryCost(Double.parseDouble(orderAndParcelNumber[0]));
      List<Parcel> parcelList = initialiseParcelsFromUserInput(orderAndParcelNumber[1]);
      order.setParcels(parcelList);
      order.setDeliveredParcels(new ArrayList<>());
    } catch (Exception e) {
      printToConsole(ERROR_MESSAGE_BASE_DELIVERY_COST_OR_PARCEL_NUMBER);
      initialiseOrderFromUserInput();
    }
    return order;
  }

  public static List<Parcel> initialiseParcelsFromUserInput(String s) {
    printToConsole(PARCEL_INFO_MESSAGE);
    List<Parcel> parcelList = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(s); i++) {
      try {
        String[] parcelInfo = readLine().split(ALL_SPACE_REGEX);
        parcelList.add(new Parcel(parcelInfo[0], Double.parseDouble(parcelInfo[1]), Double.parseDouble(parcelInfo[2]), parcelInfo[3]));
      } catch (Exception e) {
        printToConsole(ERROR_MESSAGE_PARCEL);
        initialiseParcelsFromUserInput(s);
      }
    }
    return parcelList;
  }

  public static List<Vehicle> getVehicleInfoFromUserInput() {
    printToConsole(VEHICLE_INFO);
    String vehicleInfo;
    List<Vehicle> vehicles = null;
    try {
      vehicleInfo = readLine();
      String[] vehicleInfoArray = vehicleInfo.split(ALL_SPACE_REGEX);
      int numberOfVehicles = Integer.parseInt(vehicleInfoArray[0]);
      Double maxSpeed = Double.parseDouble(vehicleInfoArray[1]);
      Double maxLoad = Double.parseDouble(vehicleInfoArray[2]);
      vehicles = new ArrayList<>();
      for (int i = 0; i < numberOfVehicles; i++) {
        vehicles.add(new Vehicle(0.0, maxSpeed, maxLoad));
      }
    } catch (IOException e) {
      printToConsole(ERROR_MESSAGE_VEHICLE);
      getVehicleInfoFromUserInput();
    }
    return vehicles;
  }

  private static String readLine() throws IOException {
    return bufferedReader.readLine();
  }
}
