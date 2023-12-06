package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationItem {

  private String id;

  private String refNo;

  private ReservationOrderStatus status;

  private LocalDate checkIn;

  private LocalDate checkOut;

}
