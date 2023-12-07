package com.assessment.hotelbooking.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppUtility {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

  public static LocalTime parseTime(String value) {
    return TIME_FORMATTER.parse(value, LocalTime::from);
  }

}
