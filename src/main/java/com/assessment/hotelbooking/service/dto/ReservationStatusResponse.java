package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationStatusResponse {
  private String id;

  private String refNo;

  private ReservationOrderStatus status;

  private Boolean isFinished;
}
