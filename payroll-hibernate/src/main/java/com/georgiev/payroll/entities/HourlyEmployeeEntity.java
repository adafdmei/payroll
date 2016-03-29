package com.georgiev.payroll.entities;

import java.math.BigDecimal;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import org.hibernate.validator.constraints.NotEmpty;

public class HourlyEmployeeEntity extends EmployeeEntity {

  @NotEmpty
  @OneToOne(fetch = FetchType.LAZY)
  private BigDecimal hourlyRate;

}
