package com.assessment.hotelbooking.service.steps.processorder;

import static com.assessment.hotelbooking.constant.Constant.PERFORM_PAYMENT_STEP_ID;

import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.assessment.hotelbooking.service.factory.ReservationHandlerFactory;
import java.util.Set;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(PERFORM_PAYMENT_STEP_ID)
public class PerformPaymentStep extends AbstractOrderProcessStep {

  private static final String STEP_NAME = "Perform payment for reservation order using linked payment method";

  public PerformPaymentStep(
      ReservationHandlerFactory handlerFactory,
      ReservationOrderRepository orderRepository) {
    super(handlerFactory, orderRepository);
  }

  @Override
  protected Set<ReservationOrderStatus> previousStatuses() {
    return Set.of(ReservationOrderStatus.PENDING_PAYMENT, ReservationOrderStatus.PAYMENT_FAILED);
  }

  @Override
  protected ReservationOrderStatus successStatus() {
    return ReservationOrderStatus.UPCOMING;
  }

  @Override
  protected String stepName() {
    return STEP_NAME;
  }
}
