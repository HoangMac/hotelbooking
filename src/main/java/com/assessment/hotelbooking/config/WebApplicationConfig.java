package com.assessment.hotelbooking.config;

import com.assessment.hotelbooking.config.interceptor.CustomerProfileInterceptor;
import com.assessment.hotelbooking.config.interceptor.JWTTokenExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebApplicationConfig implements WebMvcConfigurer {

  private final CustomerProfileInterceptor customerProfileInterceptor;

  private final JWTTokenExtractor jwtTokenExtractor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtTokenExtractor)
        .addPathPatterns("/hotel-booking/reservations/**");
    registry.addInterceptor(customerProfileInterceptor)
        .addPathPatterns("/hotel-booking/reservations/**");
  }
}
