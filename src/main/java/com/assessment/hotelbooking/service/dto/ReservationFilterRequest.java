package com.assessment.hotelbooking.service.dto;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFilterRequest {

  private LocalDate fromDate;

  private LocalDate toDate;

  private ReservationOrderStatus status;

//  @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC, sort = "")
  private Pageable pageable;
}
