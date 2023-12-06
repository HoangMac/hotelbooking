package com.assessment.hotelbooking.service.handler.impl;

import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.entity.PaymentStatus;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.service.handler.ReservationOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetryPaymentOrderHandlerImpl implements ReservationOrderHandler {

  private final ReservationOrderRepository reservationOrderRepository;

  @Override
  public void process(ReservationOrder reservationOrder) {
    reservationOrder.setPaymentStatus(PaymentStatus.PAID);
    reservationOrderRepository.save(reservationOrder);
  }
}
