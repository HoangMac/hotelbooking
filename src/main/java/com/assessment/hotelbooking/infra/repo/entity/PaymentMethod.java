package com.assessment.hotelbooking.infra.repo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PaymentMethod extends AbstractEntity {

  @Id
  @Column(name = "id")
  private String id;

  @NotBlank
  @Column(name = "customer_id")
  private String customerId;

  @NotNull
  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private PaymentType type;

  @NotNull
  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private PaymentMethodStatus status;

  @NotNull
  @Column(name = "is_default")
  private Boolean isDefault;

  @Column(name = "info")
  private String info;
}
