package com.assessment.hotelbooking.service.rules;

import static com.assessment.hotelbooking.constant.Constant.AGE_RESTRICTION_RULE_ID;
import static com.assessment.hotelbooking.infra.exception.ErrorCode.MINIMUM_AGE_RESTRICTED;

import com.assessment.hotelbooking.config.HotelRegulationConfig;
import com.assessment.hotelbooking.infra.exception.DomainException;
import com.assessment.hotelbooking.infra.exception.ErrorCode;
import com.assessment.hotelbooking.infra.repo.CustomerRepository;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(AGE_RESTRICTION_RULE_ID)
public class AgeRestrictionRule implements ValidateRule<ReservationOrder> {

  private final CustomerRepository customerRepository;

  private final HotelRegulationConfig regulationConfig;

  @Override
  public ValidateResult validate(ReservationOrder order) {
    var customer = customerRepository.findById(order.getCustomerId())
        .orElseThrow(() -> new DomainException(ErrorCode.PROFILE_NOT_FOUND));
    long customerAge = ChronoUnit.YEARS.between(customer.getDateOfBirth(), LocalDate.now());
    if (customerAge < regulationConfig.getMinimumAge()) {
      return failedResult(MINIMUM_AGE_RESTRICTED);
    }
    return passedResult();
  }
}
