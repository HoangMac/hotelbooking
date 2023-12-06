package com.assessment.hotelbooking.infra.repo;

import com.assessment.hotelbooking.infra.repo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
