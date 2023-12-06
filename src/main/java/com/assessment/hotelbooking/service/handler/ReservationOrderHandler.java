package com.assessment.hotelbooking.service.handler;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;

public interface ReservationOrderHandler {

  void process(ReservationOrder reservationOrder);

}
