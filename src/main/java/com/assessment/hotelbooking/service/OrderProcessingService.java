package com.assessment.hotelbooking.service;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.service.dto.ReservationInitRequest;

public interface OrderProcessingService {

  void processReservationOrder(String orderId);
}
