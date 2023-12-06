package com.assessment.hotelbooking.service.rules;

import static com.assessment.hotelbooking.service.rules.ValidateStatus.FAILED;
import static com.assessment.hotelbooking.service.rules.ValidateStatus.PASSED;

import com.assessment.hotelbooking.infra.exception.ErrorCode;

public interface ValidateRule<T> {

  ValidateResult validate(T context);

  default ValidateResult failedResult(ErrorCode errorCode) {
    return ValidateResult.builder()
        .errorCode(errorCode)
        .status(FAILED)
        .build();
  }

  default ValidateResult passedResult() {
    return ValidateResult.builder()
        .status(PASSED)
        .build();
  }
}
