package com.assessment.hotelbooking.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

  // Request
  public static final String CUSTOMER_ID_HEADER = "customerId";
  public static final String PROFILE_ATTRIBUTE = "customer_profile";


  // Response fields
  public static final String ERRORS = "errors";
  public static final String ERROR_CODE = "errorCode";
  public static final String ERROR_MSG = "errorMessage";

  // Error Message
  public static final String FROM_DATE_INVALID_MESSAGE = "From time is invalid format";
  public static final String TO_DATE_INVALID_MESSAGE = "To time is invalid format";
  public static final String STATUS_INVALID_MESSAGE = "Status is invalid format";


  // Others
  public static final Integer VALIDATE_ORDER_STEP_ID = 1;
  public static final Integer PERFORM_PAYMENT_STEP_ID = 2;


  public static final Integer AGE_RESTRICTION_RULE_ID = 1;
  public static final Integer OCCUPANCY_LIMIT_RULE_ID = 2;
}
