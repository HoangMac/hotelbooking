package com.assessment.hotelbooking.service;

import com.assessment.hotelbooking.service.dto.ReservationDetailResponse;
import com.assessment.hotelbooking.service.dto.ReservationFilterRequest;
import com.assessment.hotelbooking.service.dto.ReservationInitRequest;
import com.assessment.hotelbooking.service.dto.ReservationInitResponse;
import com.assessment.hotelbooking.service.dto.ReservationSearchResponse;
import com.assessment.hotelbooking.service.dto.ReservationStatusResponse;
import com.assessment.hotelbooking.service.dto.ReservationUpdateRequest;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface ReservationService {

  ReservationSearchResponse searchAndFilter(ReservationFilterRequest request, Pageable pageable);

  ReservationInitResponse initOrder(ReservationInitRequest request);

  void cancelOrder(UUID reservationId);

  ReservationDetailResponse getDetailOrder(UUID reservationId);

  ReservationStatusResponse getOrderStatus(UUID reservationId);

  void updateOrder(UUID reservationId, ReservationUpdateRequest updateRequest);
}
