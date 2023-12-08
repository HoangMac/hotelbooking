package com.assessment.hotelbooking.service.connector;

import com.assessment.hotelbooking.service.dto.LoginRequest;
import com.assessment.hotelbooking.service.dto.LoginResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

// This is a mock feign client to token management service to retrieve jwt token

//@FeignClient(
//    value = "tms-connector", url = "${feign.client.config.token-management-service.url}", path = "/tms")
@Component
public class TokenManagementConnector {


//  @PostMapping("/authorize")
  public LoginResponse loginByUsernameAndPasscode(@RequestBody LoginRequest authorizeRequest) {
    return LoginResponse.builder()
        .accessToken("a03f5242-89e7-4b5d-9a2a-789012345678")
        .expiredIn(300)
        .build();
  }
}
