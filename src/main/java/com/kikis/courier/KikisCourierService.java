package com.kikis.courier;

import com.kikis.courier.domain.Order;
import com.kikis.courier.domain.Parcel;
import com.kikis.courier.service.DiscountService;
import com.kikis.courier.service.PricingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KikisCourierService {

  public void start() {
    System.out.println("Welcome to Kiki's Courier Service!!");
    System.out.println("Please enter an option (1/2)");
    System.out.println("1. Place an order and get price for each parcel");
    System.out.println("1. Place an order and get price and estimated time of delivery for each parcel");
    Scanner scanner = new Scanner(System.in);

    calculatePrice(scanner);

  }

  private void calculatePrice(Scanner scanner) {
    System.out.println("Please enter base delivery cost for order with number of packages separated by a space");
    DiscountService discountService = new DiscountService();
    PricingService pricingService = new PricingService(discountService);
    String line1 = scanner.nextLine();
    String[] line1Array = line1.split(" ");
    Order order = new Order();
    order.setBaseDeliveryCost(Double.parseDouble(line1Array[0]));
    List<Parcel> parcelList = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(line1Array[1]); i++) {
      String[] parcelInfo = scanner.nextLine().split(" ");
      parcelList.add(new Parcel(parcelInfo[0], Double.parseDouble(parcelInfo[1]), Double.parseDouble(parcelInfo[2]), parcelInfo[3]));
    }
    order.setParcels(parcelList);
    pricingService.calculatePriceForOrder(order);
  }
}
