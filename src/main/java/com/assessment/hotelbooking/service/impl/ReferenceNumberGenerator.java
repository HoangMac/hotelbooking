package com.assessment.hotelbooking.service.impl;

import com.assessment.hotelbooking.config.HotelRegulationConfig;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReferenceNumberGenerator {

  public static final int RANDOM_LENGTH = 3;
  private static final String PADDING_CHARACTER = "0";
  private static final String RN_DATETIME_FORMAT = "yyMMddHHmmss";

  private final HotelRegulationConfig regulationConfig;

  // To use RedisAtomicInteger with expire time
  private final AtomicInteger counter = new AtomicInteger(0);

  public String generateRefNo() {
    var rrnStringBuilder = new StringBuilder();
    rrnStringBuilder.append(regulationConfig.getCode());
    rrnStringBuilder.append(getCurrentTime());
    var incremental = String.valueOf(getIncrementalNumber());
    rrnStringBuilder.append(incremental.length() < RANDOM_LENGTH ? StringUtils
        .leftPad(incremental, RANDOM_LENGTH, PADDING_CHARACTER) : incremental);
    return rrnStringBuilder.toString();
  }

  private int getIncrementalNumber() {
    return counter.incrementAndGet();
  }

  private String getCurrentTime() {
    return LocalDateTime.now(ZoneOffset.UTC)
        .format(DateTimeFormatter.ofPattern(RN_DATETIME_FORMAT));
  }

  @Scheduled(cron = "0/1 * * * * *", zone = "UTC")
  public void resetCounter() {
    // Acquire a redis lock to avoid multiple instance run same task
    counter.set(0);
  }

}
