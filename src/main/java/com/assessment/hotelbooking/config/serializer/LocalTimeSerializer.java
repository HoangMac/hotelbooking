package com.assessment.hotelbooking.config.serializer;

import static com.assessment.hotelbooking.constant.Constant.DATE_FORMATTER;
import static com.assessment.hotelbooking.constant.Constant.TIME_FORMATTER;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalTime;

public class LocalTimeSerializer extends JsonSerializer<LocalTime> {

  @Override
  public void serialize(LocalTime localTime, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeString(localTime.format(TIME_FORMATTER));
  }
}
