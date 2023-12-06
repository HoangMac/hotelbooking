package com.assessment.hotelbooking.service.dto;

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

  private LocalDate checkIn;

  private LocalDate checkOut;

  private String roomCode;

  private Integer guestCount;
}
