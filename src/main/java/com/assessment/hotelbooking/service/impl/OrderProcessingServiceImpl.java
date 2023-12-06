package com.assessment.hotelbooking.service.impl;

import com.assessment.hotelbooking.infra.exception.DomainException;
import com.assessment.hotelbooking.infra.exception.ErrorCode;
import com.assessment.hotelbooking.infra.exception.SystemException;
import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderProcessingServiceImpl implements OrderProcessingService {

  private ReservationOrderRepository reservationOrderRepository;

  @Override
  public void processReservationOrder(String orderId) {
    var reservationOrder = reservationOrderRepository.findById(orderId)
        .orElseThrow(() -> new SystemException(ErrorCode.ORDER_NOT_FOUND.getMessage()));



  }
}
