package com.assessment.hotelbooking.service.steps.processorder;

import com.assessment.hotelbooking.infra.repo.ReservationOrderRepository;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.assessment.hotelbooking.service.dto.OrderProcessContext;
import com.assessment.hotelbooking.service.factory.ReservationHandlerFactory;
import com.assessment.hotelbooking.service.steps.StepExecute;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
public abstract class AbstractOrderProcessStep implements StepExecute<OrderProcessContext> {

  private final ReservationHandlerFactory handlerFactory;

  private final ReservationOrderRepository orderRepository;

  protected abstract Set<ReservationOrderStatus> previousStatuses();
  protected abstract ReservationOrderStatus successStatus();

  protected abstract String stepName();

  @Transactional
  public final void execute(OrderProcessContext stepContext) {
    if (isExecute(stepContext)) {
      var stepContextOrder = stepContext.getOrder();
      log.info("Processing step {} - with status: {}",
          stepName(), stepContextOrder.getStatus());
      processStep(stepContext);
      log.info("Processed step {} with status: {}",
          stepName(), stepContextOrder.getStatus());

      stepContextOrder.setStatus(successStatus());
      orderRepository.saveAndFlush(stepContextOrder);
    } else {
      log.info("Ignored - Step {} is ignored for status {}.",
          stepName(), stepContext.getOrder().getStatus());
    }
  }

  protected boolean isExecute(OrderProcessContext stepContext) {
    return previousStatuses().contains(stepContext.getOrder().getStatus());
  }

  protected void processStep(OrderProcessContext stepContext) {
    var order = stepContext.getOrder();
    handlerFactory.getInstance(order.getStatus()).process(order);
  }
}
