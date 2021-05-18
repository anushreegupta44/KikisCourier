package com.kikis.courier;

import com.kikis.courier.model.Order;
import com.kikis.courier.model.Vehicle;

import java.io.IOException;
import java.util.List;

import static com.kikis.courier.util.Printer.printDeliveryTimeParcelInfo;
import static com.kikis.courier.util.Printer.printMenuOptions;
import static com.kikis.courier.util.Printer.printPricingParcelInfo;
import static com.kikis.courier.util.Printer.printToConsole;
import static com.kikis.courier.util.Printer.printWelcomeMessage;
import static com.kikis.courier.util.UserInputReader.getOrderFromInput;
import static com.kikis.courier.util.UserInputReader.getVehicleInfoFromUserInput;
import static com.kikis.courier.util.UserInputReader.takeUserOption;

public class Runner {
  private static KikisCourierService kikisCourierService;
  private static final String ERROR_MESSAGE = "Some error in input. Please start again";


  public Runner() {
    kikisCourierService = init();
  }

  public void start() {
    printWelcomeMessage();
    printMenuOptions();
    try {
      int userOption = takeUserOption();
      switch (userOption) {
        case 1:
          Order priceOrder = getOrderFromInput();
          kikisCourierService.calculatePrice(priceOrder);
          printPricingParcelInfo(priceOrder.getParcels());
          break;
        case 2:
          Order estimatedTimeOrder = getOrderFromInput();
          List<Vehicle> vehicles = getVehicleInfoFromUserInput();
          initDeliveryService(vehicles);
          kikisCourierService.calculatePrice(estimatedTimeOrder);
          kikisCourierService.calculateEstimatedDeliveryTimeFor(estimatedTimeOrder);
          printDeliveryTimeParcelInfo(estimatedTimeOrder.getParcels());
          break;
        default:
          break;
      }
    } catch (IOException e) {
      printToConsole(ERROR_MESSAGE);
      start();
    }
  }

  private static void initDeliveryService(List<Vehicle> vehicles) {
    kikisCourierService.getDeliveryService().getVehicleService().setVehicles(vehicles);
    kikisCourierService.getDeliveryService().getShipmentService().setMaxWeight(vehicles.get(0).getMaxLoad());
  }

  private static KikisCourierService init() {
    return KikisCourierService.getInstance();
  }
}
