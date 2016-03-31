package com.georgiev.payroll.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Hourly", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class HourlyEmployeeEntity extends EmployeeEntity {

  @Column(name = "HOURLY_RATE", length = 20, nullable = true)
  private BigDecimal hourlyRate;

  public BigDecimal getHourlyRate() {
    return hourlyRate;
  }

  public void setHourlyRate(BigDecimal hourlyRate) {
    this.hourlyRate = hourlyRate;
  }
}
