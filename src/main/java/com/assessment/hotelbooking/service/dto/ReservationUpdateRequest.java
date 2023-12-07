package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.validation.UUIDFormat;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateRequest {

  private LocalDate checkInDate;

  private LocalDate checkOutDate;

  @Min(0)
  private Integer guestCount;

  private String roomCode;

}
