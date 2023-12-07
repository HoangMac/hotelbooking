package com.assessment.hotelbooking.config;

import com.assessment.hotelbooking.config.interceptor.CustomerProfileInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebApplicationConfig implements WebMvcConfigurer {

  private final CustomerProfileInterceptor customerProfileInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(customerProfileInterceptor)
        .addPathPatterns("/hotel-booking/**");
  }
}
