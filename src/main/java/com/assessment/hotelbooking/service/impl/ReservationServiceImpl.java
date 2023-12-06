package com.assessment.hotelbooking.service.impl;

import static com.assessment.hotelbooking.infra.exception.ErrorCode.ORDER_NOT_FOUND;

import com.assessment.hotelbooking.config.HotelRegulationConfig;
import com.assessment.hotelbooking.infra.exception.DomainException;
import com.assessment.hotelbooking.infra.exception.ErrorCode;
import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.entity.PaymentStatus;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.assessment.hotelbooking.infra.repo.spec.ReservationSpec;
import com.assessment.hotelbooking.service.OrderProcessingService;
import com.assessment.hotelbooking.service.ReservationService;
import com.assessment.hotelbooking.service.dto.ReservationDetailResponse;
import com.assessment.hotelbooking.service.dto.ReservationFilterRequest;
import com.assessment.hotelbooking.service.dto.ReservationInitRequest;
import com.assessment.hotelbooking.service.dto.ReservationInitResponse;
import com.assessment.hotelbooking.service.dto.ReservationItem;
import com.assessment.hotelbooking.service.dto.ReservationSearchResponse;
import com.assessment.hotelbooking.service.dto.ReservationStatusResponse;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  private final CustomerProfileHolder profileHolder;
  private final ReservationOrderRepository reservationOrderRepository;

  private final OrderProcessingService orderProcessingService;

  private final HotelRegulationConfig regulationConfig;

  @Override
  public ReservationSearchResponse searchAndFilter(ReservationFilterRequest request) {
    var customerId = profileHolder.loadProfile().getId();
    log.info("Process reservation search request for customer-id : {}", customerId);
    var reservationOrderSpec = ReservationSpec.filterBy(
        request);
    var reservationOrdersPage = reservationOrderRepository.searchOrders(customerId,
        reservationOrderSpec, request.getPageable());
    return buildSearchResponse(reservationOrdersPage.getContent());
  }

  @Override
  public ReservationInitResponse initOrder(ReservationInitRequest request) {
    var reservationOrder = reservationOrderRepository.save(ReservationOrder.builder()
        .id(UUID.randomUUID().toString())
        .customerId(profileHolder.loadProfile().getId())
        .refNo("")
        .status(ReservationOrderStatus.INITIAL)
        .checkInDate(request.getCheckIn())
        .checkInTime(regulationConfig.getCheckInTime())
        .checkOutDate(request.getCheckOut())
        .checkOutTime(regulationConfig.getCheckOutTime())
        .nightsCount(Duration.between(request.getCheckIn(), request.getCheckOut()).toDays())
        .roomCode(request.getRoomCode())
        .paymentStatus(PaymentStatus.UNPAID)
        .guestCount(request.getGuestCount())
        .build());

    log.info("Created reservation order for customer-id : {}, order-id: {}, ref-no: {}",
        profileHolder.loadProfile().getId(), reservationOrder.getId(), reservationOrder.getRefNo());

    // Publish msg to a Message Queue (for ex : AWS SQS with DLQ configured) for retry mechanism
    // and asynchronous processing. Return reservationOrder-id to FrontEnd for polling. Stop the
    // process here

    // Consume msg from that Message Queue
    orderProcessingService.processReservationOrder(reservationOrder.getId());

    return ReservationInitResponse.builder()
        .id(reservationOrder.getId())
        .refNo(reservationOrder.getRefNo())
        .build();
  }

  @Override
  @Transactional
  public void cancelOrder(UUID reservationId) {
    var customerId = profileHolder.loadProfile().getId();
    var reservationOrder = reservationOrderRepository.findByIdAndCustomerId(
            reservationId.toString(), customerId)
        .orElseThrow(() -> new DomainException(ORDER_NOT_FOUND));

    if (ReservationOrderStatus.VALID_FOR_CANCELLATION.contains(reservationOrder.getStatus())) {
      log.info("Process cancellation request for order-id : {}", reservationOrder.getId());
      reservationOrder.setStatus(ReservationOrderStatus.CANCELLED);
      reservationOrderRepository.save(reservationOrder);
    } else {
      throw new DomainException(ErrorCode.INVALID_CANCEL);
    }
  }

  @Override
  public ReservationDetailResponse getDetailOrder(UUID reservationId) {
    return null;
  }

  @Override
  public ReservationStatusResponse getOrderStatus(UUID reservationId) {
    var customerId = profileHolder.loadProfile().getId();
    var reservationOrder = reservationOrderRepository.findByIdAndCustomerId(
            reservationId.toString(), customerId)
        .orElseThrow(() -> new DomainException(ORDER_NOT_FOUND));
    log.info("Process cancellation request for order-id : {}", reservationOrder.getId());
    return ReservationStatusResponse.builder()
        .id(reservationOrder.getId())
        .refNo(reservationOrder.getRefNo())
        .status(reservationOrder.getStatus())
        .isFinished(ReservationOrderStatus.FINISHED.contains(reservationOrder.getStatus()))
        .build();
  }

  private ReservationSearchResponse buildSearchResponse(List<ReservationOrder> content) {
    return ReservationSearchResponse.builder()
        .orders(content.stream().map(this::mapReservationItem).collect(Collectors.toList()))
        .build();
  }

  private ReservationItem mapReservationItem(ReservationOrder reservationOrder) {
    return ReservationItem.builder()
        .id(reservationOrder.getId())
        .refNo(reservationOrder.getRefNo())
        .checkIn(reservationOrder.getCheckInDate())
        .checkOut(reservationOrder.getCheckOutDate())
        .build();
  }
}
