package com.assessment.hotelbooking.service.impl;

import static com.assessment.hotelbooking.service.impl.TestUtil.MOCK_CUSTOMER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.assessment.hotelbooking.config.HotelRegulationConfig;
import com.assessment.hotelbooking.infra.repo.PaymentMethodRepository;
import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.RoomInfoRepository;
import com.assessment.hotelbooking.infra.repo.entity.Customer;
import com.assessment.hotelbooking.infra.repo.entity.PaymentStatus;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.assessment.hotelbooking.service.OrderProcessingService;
import com.assessment.hotelbooking.service.dto.ReservationInitRequest;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

  @Mock
  private CustomerProfileHolder profileHolder;

  @Mock
  private ReservationOrderRepository reservationOrderRepository;

  @Mock
  private OrderProcessingService orderProcessingService;

  @Mock
  private ReferenceNumberGenerator referenceNumberGenerator;

  @Mock
  private RoomInfoRepository roomInfoRepository;

  @Mock
  private PaymentMethodRepository paymentMethodRepository;

  @Mock
  private HotelRegulationConfig regulationConfig;

  @InjectMocks
  private ReservationServiceImpl testClazz;

  @Captor
  private ArgumentCaptor<ReservationOrder> orderCaptor;

  @Test
  void testInitOrder_givenValidRequest_ShouldSaveSuccessfully() {
    when(profileHolder.loadProfile()).thenReturn(Customer.builder().id(MOCK_CUSTOMER_ID).build());
    when(referenceNumberGenerator.generateRefNo()).thenReturn("Sample ref no");
    when(reservationOrderRepository.save(any())).thenAnswer(a -> a.getArguments()[0]);

    var checkInDate = LocalDate.of(2024, 10, 3);
    var checkOutDate = LocalDate.of(2024, 10, 4);
    var initRequest = ReservationInitRequest.builder()
        .checkIn(checkInDate)
        .checkOut(checkOutDate)
        .guestCount(1)
        .roomCode("A100")
        .build();
    testClazz.initOrder(initRequest);

    verify(reservationOrderRepository, times(1)).save(orderCaptor.capture());
    var savedOrder = orderCaptor.getValue();
    assertEquals(MOCK_CUSTOMER_ID, savedOrder.getCustomerId());
    assertEquals("Sample ref no", savedOrder.getRefNo());
    assertEquals(ReservationOrderStatus.INITIAL, savedOrder.getStatus());
    assertEquals(checkInDate, savedOrder.getCheckInDate());
    assertEquals(checkOutDate, savedOrder.getCheckOutDate());
    assertEquals(1, savedOrder.getGuestCount());
    assertEquals("A100", savedOrder.getRoomCode());
    assertEquals(PaymentStatus.UNPAID, savedOrder.getPaymentStatus());

    verify(orderProcessingService, times(1)).processReservationOrder(savedOrder.getId());
  }
}