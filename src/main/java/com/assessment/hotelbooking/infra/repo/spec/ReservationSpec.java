package com.assessment.hotelbooking.infra.repo.spec;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import com.assessment.hotelbooking.service.dto.ReservationFilterRequest;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class ReservationSpec {

  private static final String CUSTOMER_ID = "customerId";
  private static final String CHECK_IN = "checkIn";
  private static final String CHECK_OUT = "checkOut";
  private static final String STATUS = "status";

  public static Specification<ReservationOrder> filterBy(ReservationFilterRequest filterRequest) {
    return Specification.where(withCustomerId(filterRequest.getCustomerId())
        .and(fromDate(filterRequest.getFromDate()))
        .and(toDate(filterRequest.getToDate()))
        .and(hasStatus(filterRequest.getStatus())));
  }

  private static Specification<ReservationOrder> withCustomerId(String customerId) {
    return (root, query, builder) -> customerId == null ? builder.conjunction()
        : builder.equal(root.get(CUSTOMER_ID), customerId);
  }

  private static Specification<ReservationOrder> fromDate(LocalDate fromDate) {
    return (root, query, builder) -> fromDate == null ? builder.conjunction()
        : builder.greaterThanOrEqualTo(root.get(CHECK_IN), fromDate);
  }

  private static Specification<ReservationOrder> toDate(LocalDate toDate) {
    return (root, query, builder) -> toDate == null ? builder.conjunction()
        : builder.lessThanOrEqualTo(root.get(CHECK_OUT), toDate);
  }

  private static Specification<ReservationOrder> hasStatus(ReservationOrderStatus status) {
    return (root, query, builder) -> status == null ? builder.conjunction()
        : builder.equal(root.get(STATUS).as(ReservationOrderStatus.class), status);
  }
}
