package com.assessment.hotelbooking.service;

import com.assessment.hotelbooking.service.dto.LoginRequest;
import com.assessment.hotelbooking.service.dto.LoginResponse;

public interface AuthenticationService {

  LoginResponse login(LoginRequest loginRequest);

}
