package com.assessment.hotelbooking.infra.repo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Entity
@Table
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class RoomInfo extends AbstractEntity {

  @Id
  @Column(name = "code")
  private String code;

  @NotNull
  @Min(value = 1)
  @Column(name = "max_capacity")
  private Integer maxCapacity;

  @Column(name = "description")
  private String description;

  @Column(name = "is_locked")
  private Boolean isLocked;
}
