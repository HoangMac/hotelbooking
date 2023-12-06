package com.assessment.hotelbooking.service.steps.processorder;

import static com.assessment.hotelbooking.constant.Constant.VALIDATE_ORDER_STEP_ID;

import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.assessment.hotelbooking.service.factory.ReservationHandlerFactory;
import java.util.Set;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(VALIDATE_ORDER_STEP_ID)
public class ValidateOrderStep extends AbstractOrderProcessStep {

  private static final String STEP_NAME = "Validate reservation order";

  public ValidateOrderStep(ReservationHandlerFactory handlerFactory,
      ReservationOrderRepository orderRepository) {
    super(handlerFactory, orderRepository);
  }


  @Override
  protected Set<ReservationOrderStatus> previousStatuses() {
    return Set.of(ReservationOrderStatus.INITIAL);
  }

  @Override
  protected ReservationOrderStatus successStatus() {
    return ReservationOrderStatus.PENDING_PAYMENT;
  }

  @Override
  protected String stepName() {
    return STEP_NAME;
  }
}
