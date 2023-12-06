package com.assessment.hotelbooking.service.factory;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.assessment.hotelbooking.service.handler.ReservationOrderHandler;
import java.util.Arrays;
import java.util.EnumMap;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ReservationHandlerFactory extends EnumBeanAbstract<ReservationOrderStatus, ReservationOrderHandler> {

  @PostConstruct
  private void init() {
    this.enumMap = new EnumMap<>(ReservationOrderStatus.class);
    Arrays.stream(ReservationOrderStatus.values())
        .forEach(orderStatus -> enumMap.put(orderStatus,
            orderStatus.handler(applicationContext)));
  }
}
