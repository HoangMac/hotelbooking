package com.assessment.hotelbooking.service;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;

public interface OrderValidationService {

  void validate(ReservationOrder context);

}
