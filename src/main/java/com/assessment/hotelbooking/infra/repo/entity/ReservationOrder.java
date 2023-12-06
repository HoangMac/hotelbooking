package com.assessment.hotelbooking.infra.repo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
public class ReservationOrder extends AbstractEntity {

  @Id
  @Column(name = "id")
  private String id;

  @NotBlank
  @Column(name = "customer_id")
  private String customerId;

  @NotBlank
  @Column(name = "ref_no")
  private String refNo;

  @NotNull
  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private ReservationOrderStatus status;

  @NotNull
  @Column(name = "check_in_date")
  private LocalDate checkInDate;

  @Column(name = "check_in_time")
  private LocalTime checkInTime;

  @NotNull
  @Column(name = "check_out")
  private LocalDate checkOutDate;

  @Column(name = "check_out_time")
  private LocalTime checkOutTime;

  @Transient
  private Long nightsCount;

  @Column(name = "payment_method_id")
  private String paymentMethodId;

  @NotBlank
  @Column(name = "roomCode")
  private String roomCode;

  @NotNull
  @Column(name = "guest_count")
  private Integer guestCount;

  @NotNull
  @Column(name = "payment_status")
  @Enumerated(EnumType.STRING)
  private PaymentStatus paymentStatus;
}
