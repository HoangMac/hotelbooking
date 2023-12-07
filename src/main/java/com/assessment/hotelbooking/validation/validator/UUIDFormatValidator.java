package com.assessment.hotelbooking.validation.validator;

import com.assessment.hotelbooking.validation.UUIDFormat;
import java.util.UUID;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UUIDFormatValidator implements ConstraintValidator<UUIDFormat, String> {

  @Override
  public boolean isValid(String uuidString, ConstraintValidatorContext cx) {
    if (uuidString == null) {
      return true;
    }
    try {
      UUID.fromString(uuidString);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }
}