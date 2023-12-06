package com.assessment.hotelbooking.infra.repo;

import com.assessment.hotelbooking.infra.repo.entity.PaymentMethod;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {

  List<PaymentMethod> findByCustomerId(String customerId);
}
