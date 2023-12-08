package com.assessment.hotelbooking.controller;

import com.assessment.hotelbooking.service.AuthenticationService;
import com.assessment.hotelbooking.service.dto.LoginRequest;
import com.assessment.hotelbooking.service.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/hotel-booking")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @ApiResponse(description = "Search and filter reservation orders")
  @Operation(summary = "Search and filter reservation orders")
  @PostMapping("/login")
  public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
    return authenticationService.login(loginRequest);
  }
}
