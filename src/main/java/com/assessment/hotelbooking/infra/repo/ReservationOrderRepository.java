package com.assessment.hotelbooking.infra.repo;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrderStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationOrderRepository extends JpaRepository<ReservationOrder, String> {

  Page<ReservationOrder> searchOrders(String customerId, Specification<ReservationOrder> specification,
      Pageable pageable);

  Optional<ReservationOrder> findByIdAndCustomerId(String id, String customerId);
}
