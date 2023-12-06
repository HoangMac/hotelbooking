package com.assessment.hotelbooking.infra.repo.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public abstract class AbstractEntity extends Auditable {

  @Version
  @Builder.Default
  private Integer version = -1;
}
