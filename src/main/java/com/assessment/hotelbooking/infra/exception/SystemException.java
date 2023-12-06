package com.assessment.hotelbooking.infra.exception;

public class SystemException extends RuntimeException {

  public SystemException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public SystemException(String message) {
    super(message);
  }

}
