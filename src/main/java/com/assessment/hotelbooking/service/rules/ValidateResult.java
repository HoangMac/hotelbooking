package com.assessment.hotelbooking.service.rules;

import static com.assessment.hotelbooking.service.rules.ValidateStatus.FAILED;
import static com.assessment.hotelbooking.service.rules.ValidateStatus.PASSED;

import com.assessment.hotelbooking.infra.exception.ErrorCode;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidateResult {

  private ValidateStatus status;

  private ErrorCode errorCode;

  public boolean isPassed() {
    return PASSED == this.status;
  }

  public boolean isFailed() {
    return FAILED == this.status;
  }
}
