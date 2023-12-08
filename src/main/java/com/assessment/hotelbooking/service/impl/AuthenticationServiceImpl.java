package com.assessment.hotelbooking.service.impl;

import com.assessment.hotelbooking.service.AuthenticationService;
import com.assessment.hotelbooking.service.connector.TokenManagementConnector;
import com.assessment.hotelbooking.service.dto.LoginRequest;
import com.assessment.hotelbooking.service.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final TokenManagementConnector tokenManagementConnector;

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    return tokenManagementConnector.loginByUsernameAndPasscode(loginRequest);
  }
}
