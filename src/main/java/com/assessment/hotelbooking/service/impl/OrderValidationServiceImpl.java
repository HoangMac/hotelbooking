package com.assessment.hotelbooking.service.impl;

import com.assessment.hotelbooking.infra.exception.DomainException;
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

  @Override
  public void validate(ReservationOrder order) {
    for (var aRule : validationRules) {
      var validateResult = aRule.validate(order);
      if (validateResult.isFailed()) {
        throw new DomainException(validateResult.getErrorCode());
      }
    }
  }
}
