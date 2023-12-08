package com.assessment.hotelbooking.config.interceptor;

import static com.assessment.hotelbooking.constant.Constant.AUTHORIZATION_HEADER;
import static com.assessment.hotelbooking.constant.Constant.CUSTOMER_ID_HEADER;
import static com.assessment.hotelbooking.constant.Constant.ERRORS;
import static com.assessment.hotelbooking.constant.Constant.ERROR_MSG;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JWTTokenExtractor implements HandlerInterceptor {

  private final ObjectMapper objectMapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    var authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.isBlank(authorizationHeader)) {
      writeResponseError(response, "Missing Authorization Header");
      return false;
    }

    request.setAttribute(CUSTOMER_ID_HEADER, authorizationHeader.replace("Bearer ", ""));
    return true;
  }

  private void writeResponseError(HttpServletResponse response, String msg) throws IOException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    var responseJson = Map.of(
        ERRORS, List.of(
            Map.of(
                ERROR_MSG, msg
            )
        )
    );
    objectMapper.writeValue(response.getWriter(), responseJson);
  }
}
