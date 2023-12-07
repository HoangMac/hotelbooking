package com.assessment.hotelbooking.config;

import static com.fasterxml.jackson.databind.cfg.CoercionAction.AsNull;
import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.EmptyString;
import static com.fasterxml.jackson.databind.type.LogicalType.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class AppConfiguration {

  @Bean
  public ObjectMapper om() {
    ObjectMapper om = new ObjectMapper();
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    om.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    om.coercionConfigFor(Map).setCoercion(EmptyString, AsNull);
//    om.registerModule(new JavaTimeModule());
    return om;
  }
}
