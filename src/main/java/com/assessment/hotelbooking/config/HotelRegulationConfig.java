package com.assessment.hotelbooking.config;

import java.time.LocalTime;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "hotel-regulations")
public class HotelRegulationConfig {

  private LocalTime checkInTime;

  private LocalTime checkOutTime;

  private Long minimumAge;
}
