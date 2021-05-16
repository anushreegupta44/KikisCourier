package com.kikis.courier.util;

import com.kikis.courier.domain.Parcel;

import java.util.List;

public class Printer {
  public static void printToConsole(String info) {
    System.out.println(info);
  }

  public static void printParcelInfo(List<Parcel> parcels) {
    printToConsole("Pricing Info for parcels in order");
    for (Parcel parcel : parcels) {
      printToConsole(parcel.toString());
    }
  }

  public static void printMenuOptions() {
    printToConsole("Please enter an option (1/2)");
    printToConsole("1. Place an order and get price for each parcel");
    printToConsole("2. Place an order and get price and estimated time of delivery for each parcel");
  }

  public static void printWelcomeMessage() {
    printToConsole("Welcome to Kiki's Courier Service!!");
  }
}
