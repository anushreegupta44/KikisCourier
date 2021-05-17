package com.kikis.courier.util;

import com.kikis.courier.model.Parcel;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static com.kikis.courier.util.Printer.printMenuOptions;
import static com.kikis.courier.util.Printer.printParcelInfo;
import static com.kikis.courier.util.Printer.printToConsole;
import static com.kikis.courier.util.Printer.printWelcomeMessage;
import static org.junit.Assert.assertEquals;

public class PrinterTest {
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  public void shouldPrintToConsole() {
    printToConsole("Welcome  to KIKIS!!");

    assertEquals("Welcome  to KIKIS!!", outputStreamCaptor.toString().trim());
  }

  @Test
  public void shouldPrintMenuOptions() {
    printMenuOptions();

    assertEquals("Please enter an option (1/2)\n1. Place an order and get price for each parcel\n" +
                         "2. Place an order and get price and estimated time of delivery for each parcel",
            outputStreamCaptor.toString().trim());
  }

  @Test
  public void shouldPrintWelcomeMessage() {
    printWelcomeMessage();

    assertEquals("Welcome to Kiki's Courier Service!!", outputStreamCaptor.toString().trim());
  }

  @Test
  public void shouldPrintParcelInfo() {
    Parcel parcel = new Parcel("parcel1", 12.0, 20.0, "coupon1");
    parcel.setCostToDeliver(100.0);
    parcel.setTotalDiscount(22.1);

    printParcelInfo(List.of(parcel));

    assertEquals("Pricing Info for parcels in order\nparcel1 22.1 100.0", outputStreamCaptor.toString().trim());
  }
}
