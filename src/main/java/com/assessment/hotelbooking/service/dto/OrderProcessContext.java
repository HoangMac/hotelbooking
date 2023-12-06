package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProcessContext {

  private ReservationOrder order;
}
