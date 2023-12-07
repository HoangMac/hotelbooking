package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.config.deserializer.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInitRequest {

  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate checkIn;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate checkOut;

  private String roomCode;

  private Integer guestCount;
}
