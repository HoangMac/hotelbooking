package com.assessment.hotelbooking.service.handler.impl;

import com.assessment.hotelbooking.infra.repo.PaymentMethodRepository;
import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.entity.PaymentMethod;
import com.assessment.hotelbooking.infra.repo.entity.PaymentMethodStatus;
import com.assessment.hotelbooking.infra.repo.entity.PaymentType;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.service.handler.ReservationOrderHandler;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendingPaymentOrderHandlerImpl implements ReservationOrderHandler {

  private final PaymentMethodRepository paymentMethodRepository;

  private final ReservationOrderRepository reservationOrderRepository;

  @Override
  public void process(ReservationOrder reservationOrder) {
    var linkedPaymentMethods = paymentMethodRepository.findByCustomerId(
        reservationOrder.getCustomerId());

    var activePaymentMethods = linkedPaymentMethods.stream()
        .filter(paymentMethod -> paymentMethod.getStatus() == PaymentMethodStatus.ACTIVE)
        .collect(Collectors.toList());

    if (activePaymentMethods.isEmpty()) {
      var newPaymentMethod = createNewDefaultPaymentMethod(
          reservationOrder.getCustomerId());
      reservationOrder.setPaymentMethodId(newPaymentMethod.getId());
      reservationOrderRepository.save(reservationOrder);
      return;
    }

    var defaultMethodOpt = activePaymentMethods.stream()
        .filter(PaymentMethod::getIsDefault).findFirst().orElse(activePaymentMethods.get(0));
    reservationOrder.setPaymentMethodId(defaultMethodOpt.getId());
    reservationOrderRepository.save(reservationOrder);
  }

  private PaymentMethod createNewDefaultPaymentMethod(String customerId) {
    return paymentMethodRepository.save(PaymentMethod.builder()
        .id(UUID.randomUUID().toString())
        .customerId(customerId)
        .type(PaymentType.CASH)
        .status(PaymentMethodStatus.ACTIVE)
        .isDefault(true)
        .info("Default payment method created by system")
        .build());
  }
}
