package com.assessment.hotelbooking.config.deserializer;

import static com.assessment.hotelbooking.constant.Constant.DATE_FORMATTER;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

  @Override
  public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {
    var date = p.getText();
    return LocalDate.parse(date, DATE_FORMATTER);
  }
}
