package com.assessment.hotelbooking.infra.repo.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
public class Customer extends AbstractEntity {

  @Id
  @Column(name = "id")
  private String id;

  @NotBlank
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "middle_name")
  private String middleName;

  @NotBlank
  @Column(name = "last_name")
  private String lastName;

  @NotNull
  @Column(name = "username", length = 32)
  private String username;

  @NotNull
  @Column(name = "status", length = 50)
  private String status;

  @Column(name = "email")
  private String email;

  @NotNull
  @Column(name = "cellphone")
  private String cellphone;

  @NotNull
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Transient
  public String getFullName() {
    return String.format("%s %s", this.firstName, this.lastName);
  }
}
