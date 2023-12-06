package com.assessment.hotelbooking.infra.exception;

public enum ServiceIdentifier {
  HOTEL_BOOKING("22");

  private final String code;

  ServiceIdentifier(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
