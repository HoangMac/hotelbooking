package com.assessment.hotelbooking.service.impl;

import static com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus.VALIDATED_FAILED;

import com.assessment.hotelbooking.infra.exception.DomainException;
import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.service.OrderValidationService;
import com.assessment.hotelbooking.service.rules.ValidateRule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderValidationServiceImpl implements OrderValidationService {

  private final List<ValidateRule<ReservationOrder>> validationRules;

  private final ReservationOrderRepository reservationOrderRepository;

  @Override
  public void validate(ReservationOrder order) {
    for (var aRule : validationRules) {
      var validateResult = aRule.validate(order);
      if (validateResult.isFailed()) {
        order.setStatus(VALIDATED_FAILED);
        reservationOrderRepository.save(order);
        throw new DomainException(validateResult.getErrorCode());
      }
    }
  }
}
