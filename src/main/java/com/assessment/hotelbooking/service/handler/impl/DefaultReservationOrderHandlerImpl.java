package com.assessment.hotelbooking.service.handler.impl;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.service.handler.ReservationOrderHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DefaultReservationOrderHandlerImpl implements
    ReservationOrderHandler {

  @Override
  public void process(ReservationOrder reservationOrder) {
    log.info("Nothing to be done here");
  }
}
