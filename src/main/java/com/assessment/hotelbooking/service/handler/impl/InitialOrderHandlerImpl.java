package com.assessment.hotelbooking.service.handler.impl;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.service.OrderValidationService;
import com.assessment.hotelbooking.service.handler.ReservationOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialOrderHandlerImpl implements ReservationOrderHandler {

  private final OrderValidationService orderValidationService;

  @Override
  public void process(ReservationOrder reservationOrder) {
    orderValidationService.validate(reservationOrder);
  }
}
