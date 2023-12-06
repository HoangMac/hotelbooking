package com.assessment.hotelbooking.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.assessment.hotelbooking.service.ReservationService;
import com.assessment.hotelbooking.service.dto.ReservationCancelResponse;
import com.assessment.hotelbooking.service.dto.ReservationDetailResponse;
import com.assessment.hotelbooking.service.dto.ReservationFilterRequest;
import com.assessment.hotelbooking.service.dto.ReservationInitRequest;
import com.assessment.hotelbooking.service.dto.ReservationInitResponse;
import com.assessment.hotelbooking.service.dto.ReservationSearchResponse;
import com.assessment.hotelbooking.service.dto.ReservationStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/hotel-booking/reservations/1.0.0")
public class HotelBookingController {

  private final ReservationService reservationService;

  @ApiResponse(description = "Search and filter reservation orders")
  @Operation(summary = "Search and filter reservation orders")
  @PostMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
  public ReservationSearchResponse searchAndFilter(
      @RequestBody(required = false) @Valid ReservationFilterRequest filterRequest) {
    return reservationService.searchAndFilter(filterRequest);
  }

  @ApiResponse(description = "Initialize a reservation orders")
  @Operation(summary = "Initialize a reservation orders")
  @PostMapping
  public ReservationInitResponse initReservation(@RequestBody @Valid ReservationInitRequest initRequest) {
    return reservationService.initOrder(initRequest);
  }

  @ApiResponse(description = "Get the status of a reservation order")
  @Operation(summary = "Get the status of a reservation orders")
  @GetMapping(value = "/{reservationId}/status", produces = APPLICATION_JSON_VALUE)
  public ReservationStatusResponse getReservationOrderStatus(
      @PathVariable("reservationId") UUID reservationId) {
    return reservationService.getOrderStatus(reservationId);
  }

  @ApiResponse(description = "Cancel a reservation order")
  @Operation(summary = "Cancel a reservation orders")
  @PostMapping(value = "/{reservationId}/cancel")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ReservationCancelResponse cancelReservation(@PathVariable("reservationID") UUID reservationId) {
    reservationService.cancelOrder(reservationId);
    return ReservationCancelResponse.builder().message("Success").build();
  }

  @ApiResponse(description = "Get detail of a reservation order")
  @Operation(summary = "Get detail of a reservation orders")
  @GetMapping(value = "/{reservationId}/", produces = APPLICATION_JSON_VALUE)
  public ReservationDetailResponse getReservationOrderDetail(
      @PathVariable("reservationId") UUID reservationId) {
    return reservationService.getDetailOrder(reservationId);
  }
}
