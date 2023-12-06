package com.assessment.hotelbooking.service.impl;

import static com.assessment.hotelbooking.constant.Constant.PROFILE_ATTRIBUTE;
import static com.assessment.hotelbooking.infra.exception.ErrorCode.PROFILE_NOT_FOUND;

import com.assessment.hotelbooking.infra.exception.DomainException;
import com.assessment.hotelbooking.infra.repo.entity.Customer;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerProfileHolder {

  private final HttpServletRequest httpServletRequest;

  public Customer loadProfile() {
    var profileInfo = httpServletRequest.getAttribute(PROFILE_ATTRIBUTE);
    return Optional.ofNullable(profileInfo)
        .map(Customer.class::cast)
        .orElseThrow(() -> new DomainException(PROFILE_NOT_FOUND));
  }
}
