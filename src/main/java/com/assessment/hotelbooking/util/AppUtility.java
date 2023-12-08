package com.assessment.hotelbooking.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class AppUtility {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

  public static LocalTime parseTime(String value) {
    if (StringUtils.isBlank(value)) {
      return null;
    }
    return TIME_FORMATTER.parse(value, LocalTime::from);
  }

}
