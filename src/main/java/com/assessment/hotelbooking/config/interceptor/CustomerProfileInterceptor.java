package com.assessment.hotelbooking.config.interceptor;

import static com.assessment.hotelbooking.constant.Constant.CUSTOMER_ID_HEADER;
import static com.assessment.hotelbooking.constant.Constant.ERRORS;
import static com.assessment.hotelbooking.constant.Constant.ERROR_CODE;
import static com.assessment.hotelbooking.constant.Constant.ERROR_MSG;
import static com.assessment.hotelbooking.constant.Constant.PROFILE_ATTRIBUTE;
import static com.assessment.hotelbooking.infra.exception.ErrorCode.PROFILE_MISSING;
import static com.assessment.hotelbooking.infra.exception.ErrorCode.PROFILE_NOT_FOUND;

import com.assessment.hotelbooking.infra.exception.ErrorCode;
import com.assessment.hotelbooking.infra.repo.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomerProfileInterceptor implements HandlerInterceptor {

  private final CustomerRepository customerRepository;

  private final ObjectMapper objectMapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    var customerId = String.valueOf(request.getAttribute(CUSTOMER_ID_HEADER));
    if (StringUtils.isNotBlank(customerId)) {
      var customerOptional = customerRepository.findById(customerId);
      if (customerOptional.isPresent()) {
        request.setAttribute(PROFILE_ATTRIBUTE, customerOptional.get());
        log.info("Customer profile identified with customer-id: {}", customerId);
        return true;
      } else {
        log.error("Cannot identify customer profile with id : {}", customerId);
        request.removeAttribute(PROFILE_ATTRIBUTE);
        writeResponseError(response, PROFILE_NOT_FOUND);
        return false;
      }
    }

    log.error("Customer-id is missing with url {}", request.getServletPath());
    request.removeAttribute(PROFILE_ATTRIBUTE);
    writeResponseError(response, PROFILE_MISSING);
    return false;
  }

  private void writeResponseError(HttpServletResponse response, ErrorCode errorCode) throws IOException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    var responseJson = Map.of(
        ERRORS, List.of(
            Map.of(
                ERROR_CODE, errorCode.toUniversalCode(),
                ERROR_MSG, errorCode.getMessage()
            )
        )
    );
    objectMapper.writeValue(response.getWriter(), responseJson);
  }
}
