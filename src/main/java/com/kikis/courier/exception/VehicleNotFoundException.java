package com.kikis.courier.exception;

public class VehicleNotFoundException extends RuntimeException {
  public VehicleNotFoundException(String message) {
    super(message);
  }
}
