package com.assessment.hotelbooking.infra.repo;

import com.assessment.hotelbooking.infra.repo.entity.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, String> {

}
