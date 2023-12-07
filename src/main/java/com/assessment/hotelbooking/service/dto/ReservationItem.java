package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.config.serializer.LocalDateSerializer;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate checkIn;

  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate checkOut;

}
