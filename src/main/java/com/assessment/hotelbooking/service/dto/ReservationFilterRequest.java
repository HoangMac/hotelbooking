package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFilterRequest {

  @JsonIgnore
  private String customerId;

  private LocalDate fromDate;

  private LocalDate toDate;

  private ReservationOrderStatus status;
}
