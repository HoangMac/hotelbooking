package com.assessment.hotelbooking.service.rules;

import static com.assessment.hotelbooking.constant.Constant.AGE_RESTRICTION_RULE_ID;
import static com.assessment.hotelbooking.constant.Constant.OCCUPANCY_LIMIT_RULE_ID;

import com.assessment.hotelbooking.infra.exception.DomainException;
import com.assessment.hotelbooking.infra.exception.ErrorCode;
import com.assessment.hotelbooking.infra.repo.RoomInfoRepository;
import com.assessment.hotelbooking.infra.repo.entity.ReservationOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(OCCUPANCY_LIMIT_RULE_ID)
public class OccupancyLimitRule implements ValidateRule<ReservationOrder> {

  private final RoomInfoRepository roomInfoRepository;

  @Override
  public ValidateResult validate(ReservationOrder order) {

    var roomInfo = roomInfoRepository.findById(order.getRoomCode())
        .orElseThrow(() -> new DomainException(ErrorCode.ROOM_NOT_FOUND));

    if (order.getGuestCount() > roomInfo.getMaxCapacity()) {
      return failedResult(ErrorCode.MAXIMUM_OCCUPANTS_REACHED);
    }

    return passedResult();
  }
}
