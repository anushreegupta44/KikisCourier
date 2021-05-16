package com.kikis.courier;

import com.kikis.courier.domain.Order;
import com.kikis.courier.service.CouponService;
import com.kikis.courier.service.DiscountService;
import com.kikis.courier.service.PricingService;

import java.io.IOException;

import static com.kikis.courier.util.Printer.printMenuOptions;
import static com.kikis.courier.util.Printer.printParcelInfo;
import static com.kikis.courier.util.Printer.printToConsole;
import static com.kikis.courier.util.Printer.printWelcomeMessage;
import static com.kikis.courier.util.UserInputReader.getOrderFromInput;
import static com.kikis.courier.util.UserInputReader.takeUserOption;

public class Runner {
  private final KikisCourierService kikisCourierService;
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
          Order order = getOrderFromInput();
          kikisCourierService.calculatePrice(order);
          printParcelInfo(order.getParcels());
          break;
        case 2:
          break;
        default:
          break;
      }
    } catch (IOException e) {
      printToConsole(ERROR_MESSAGE);
      start();
    }
  }

  private static KikisCourierService init() {
    CouponService couponService = new CouponService();
    DiscountService discountService = new DiscountService(couponService);
    PricingService pricingService = new PricingService(discountService);
    return new KikisCourierService(pricingService);
  }
}
