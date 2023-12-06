package com.assessment.hotelbooking.infra.repo.entity;

import com.assessment.hotelbooking.service.handler.ReservationOrderHandler;
import com.assessment.hotelbooking.service.handler.impl.DefaultReservationOrderHandlerImpl;
import com.assessment.hotelbooking.service.handler.impl.InitialOrderHandlerImpl;
import com.assessment.hotelbooking.service.handler.impl.PendingPaymentOrderHandlerImpl;
import com.assessment.hotelbooking.service.handler.impl.RetryPaymentOrderHandlerImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

@AllArgsConstructor
public enum ReservationOrderStatus {
  INITIAL(InitialOrderHandlerImpl.class),
  PENDING_PAYMENT(PendingPaymentOrderHandlerImpl.class),
  PAYMENT_FAILED(RetryPaymentOrderHandlerImpl.class),
  UPCOMING(DefaultReservationOrderHandlerImpl.class),
  COMPLETE(DefaultReservationOrderHandlerImpl.class),
  CANCELLED(DefaultReservationOrderHandlerImpl.class),

  ;

  private final Class<? extends ReservationOrderHandler> handlerClazz;

  public ReservationOrderHandler handler(ApplicationContext applicationContext) {
    return applicationContext.getBean(handlerClazz);
  }

  public static final List<ReservationOrderStatus> VALID_FOR_CANCELLATION = List.of(
      INITIAL, PENDING_PAYMENT, UPCOMING
  );

  public static final List<ReservationOrderStatus> FINISHED = List.of(
      UPCOMING, COMPLETE, CANCELLED
  );
}
