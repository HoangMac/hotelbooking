package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.config.serializer.LocalDateSerializer;
import com.assessment.hotelbooking.config.serializer.LocalTimeSerializer;
import com.assessment.hotelbooking.infra.repo.entity.PaymentStatus;
import com.assessment.hotelbooking.infra.repo.entity.PaymentType;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetailResponse {

  private String id;

  private String customerName;

  private String refNo;

  private ReservationOrderStatus status;

  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate checkInDate;

  @JsonSerialize(using = LocalTimeSerializer.class)
  private LocalTime checkInTime;

  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate checkOutDate;

  @JsonSerialize(using = LocalTimeSerializer.class)
  private LocalTime checkOutTime;

  private Long nightsCount;

  private PaymentType paymentMethodType;

  private PaymentStatus paymentStatus;

  private Integer guestCount;
}
