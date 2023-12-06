package com.assessment.hotelbooking.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  UNKNOWN_ERROR("000", "Unknown error"),
  INVALID_REQUEST("001", "Request is invalid"),

  PROFILE_NOT_FOUND("010", "Profile not found"),
  PROFILE_MISSING("011", "Profile id missing"),
  ORDER_NOT_FOUND("012", "Reservation order not found"),
  ROOM_NOT_FOUND("013", "Room not found"),
  INVALID_CANCEL("014", "Order status not valid for cancellation"),


  MINIMUM_AGE_RESTRICTED("021", "Customer must be >= 18 years old"),
  MAXIMUM_OCCUPANTS_REACHED("022", "Maximum occupants for room reached"),

  ;

  private final String value;

  private final String message;

  public String toUniversalCode() {
    return String.format("%s%s%s", SystemIdentifier.CORE_SYSTEM.getCode(),
        ServiceIdentifier.HOTEL_BOOKING.getCode(), value);
  }
}
