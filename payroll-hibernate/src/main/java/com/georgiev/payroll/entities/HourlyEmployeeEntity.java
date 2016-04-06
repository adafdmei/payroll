package com.georgiev.payroll.entities;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Hourly", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class HourlyEmployeeEntity extends EmployeeEntity {

  @Column(name = "HOURLY_RATE", length = 20, nullable = true)
  private BigDecimal hourlyRate;

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<TimeCardEntity> timecards;

  public void setTimecards(Set<TimeCardEntity> timecards) {
    this.timecards = timecards;
  }

  public Set<TimeCardEntity> getTimecards() {
    return timecards;
  }

  public BigDecimal getHourlyRate() {
    return hourlyRate;
  }

  public void setHourlyRate(BigDecimal hourlyRate) {
    this.hourlyRate = hourlyRate;
  }
}
