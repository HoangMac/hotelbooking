package com.assessment.hotelbooking.service.impl;

import com.assessment.hotelbooking.infra.exception.ErrorCode;
import com.assessment.hotelbooking.infra.exception.SystemException;
import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.service.OrderProcessingService;
import com.assessment.hotelbooking.service.dto.OrderProcessContext;
import com.assessment.hotelbooking.service.steps.StepExecute;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderProcessingServiceImpl implements OrderProcessingService {

  private final ReservationOrderRepository reservationOrderRepository;

  private final List<StepExecute<OrderProcessContext>> processSteps;

  @Override
  public void processReservationOrder(String orderId) {
    var reservationOrder = reservationOrderRepository.findById(orderId)
        .orElseThrow(() -> new SystemException(ErrorCode.ORDER_NOT_FOUND.getMessage()));

    var processContext = OrderProcessContext.builder().order(reservationOrder).build();

    processSteps.forEach(step -> step.execute(processContext));

  }
}
