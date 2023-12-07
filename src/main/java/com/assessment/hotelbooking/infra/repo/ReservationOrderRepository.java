package com.assessment.hotelbooking.infra.repo;

import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReservationOrderRepository extends JpaRepository<ReservationOrder, String>,
    JpaSpecificationExecutor<ReservationOrder> {

  Optional<ReservationOrder> findByIdAndCustomerId(String id, String customerId);
}
