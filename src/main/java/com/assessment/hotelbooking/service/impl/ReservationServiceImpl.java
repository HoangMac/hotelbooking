package com.assessment.hotelbooking.service.impl;

import static com.assessment.hotelbooking.infra.exception.ErrorCode.ORDER_NOT_FOUND;
import static com.assessment.hotelbooking.util.AppUtility.parseTime;

import com.assessment.hotelbooking.config.HotelRegulationConfig;
import com.assessment.hotelbooking.infra.exception.DomainException;
import com.assessment.hotelbooking.infra.exception.ErrorCode;
import com.assessment.hotelbooking.infra.repo.PaymentMethodRepository;
import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.RoomInfoRepository;
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
import com.assessment.hotelbooking.service.dto.ReservationUpdateRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  private final CustomerProfileHolder profileHolder;

  private final ReservationOrderRepository reservationOrderRepository;

  private final OrderProcessingService orderProcessingService;

  private final ReferenceNumberGenerator referenceNumberGenerator;

  private final RoomInfoRepository roomInfoRepository;

  private final PaymentMethodRepository paymentMethodRepository;

  private final HotelRegulationConfig regulationConfig;

  @Override
  public ReservationSearchResponse searchAndFilter(ReservationFilterRequest request,
      Pageable pageable) {
    var customerId = profileHolder.loadProfile().getId();
    log.info("Process reservation search request for customer-id : {}", customerId);
    request.setCustomerId(customerId);
    var reservationOrderSpec = ReservationSpec.filterBy(request);
    var reservationOrdersPage = reservationOrderRepository.findAll(
        reservationOrderSpec, pageable);
    return buildSearchResponse(reservationOrdersPage.getContent());
  }

  @Override
  public ReservationInitResponse initOrder(ReservationInitRequest request) {
    var reservationOrder = reservationOrderRepository.save(ReservationOrder.builder()
        .id(UUID.randomUUID().toString())
        .customerId(profileHolder.loadProfile().getId())
        .refNo(referenceNumberGenerator.generateRefNo())
        .status(ReservationOrderStatus.INITIAL)
        .checkInDate(request.getCheckIn())
        .checkInTime(parseTime(regulationConfig.getCheckInTime()))
        .checkOutDate(request.getCheckOut())
        .checkOutTime(parseTime(regulationConfig.getCheckOutTime()))
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
    var customer = profileHolder.loadProfile();
    var reservationOrder = reservationOrderRepository.findByIdAndCustomerId(
            reservationId.toString(), customer.getId())
        .orElseThrow(() -> new DomainException(ORDER_NOT_FOUND));

    var paymentMethod = paymentMethodRepository.findById(
            reservationOrder.getPaymentMethodId())
        .orElseThrow(() -> new DomainException(ErrorCode.PAYMENT_METHOD_NOT_FOUND));

    var roomInfo = roomInfoRepository.findById(reservationOrder.getRoomCode())
        .orElseThrow(() -> new DomainException(ErrorCode.ROOM_NOT_FOUND));

    return ReservationDetailResponse.builder()
        .id(reservationOrder.getId())
        .customerName(customer.getFullName())
        .refNo(reservationOrder.getRefNo())
        .status(reservationOrder.getStatus())
        .checkInDate(reservationOrder.getCheckInDate())
        .checkInTime(reservationOrder.getCheckInTime())
        .checkOutDate(reservationOrder.getCheckOutDate())
        .checkOutTime(reservationOrder.getCheckOutTime())
        .nightsCount(reservationOrder.getNightsCount())
        .paymentMethodType(paymentMethod.getType())
        .paymentStatus(reservationOrder.getPaymentStatus())
        .guestCount(reservationOrder.getGuestCount())
        .build();
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

  @Override
  @Transactional
  public void updateOrder(UUID reservationId, ReservationUpdateRequest updateRequest) {
    var reservationOrder = reservationOrderRepository.findById(reservationId.toString())
        .orElseThrow(() -> new DomainException(ORDER_NOT_FOUND));

    if (updateRequest.getRoomCode() != null) {
      var roomInfo = roomInfoRepository.findById(updateRequest.getRoomCode())
          .orElseThrow(() -> new DomainException(ErrorCode.ROOM_NOT_FOUND));
      reservationOrder.setRoomCode(roomInfo.getCode());
    }

    Optional.ofNullable(updateRequest.getCheckInDate()).ifPresent(reservationOrder::setCheckInDate);
    Optional.ofNullable(updateRequest.getCheckOutDate()).ifPresent(reservationOrder::setCheckOutDate);
    Optional.ofNullable(updateRequest.getGuestCount()).ifPresent(reservationOrder::setGuestCount);

    reservationOrderRepository.save(reservationOrder);
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
        .status(reservationOrder.getStatus())
        .checkIn(reservationOrder.getCheckInDate())
        .checkOut(reservationOrder.getCheckOutDate())
        .build();
  }
}
