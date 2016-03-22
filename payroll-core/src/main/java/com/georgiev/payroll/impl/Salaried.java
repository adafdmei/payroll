package com.georgiev.payroll.impl;

import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.domain.Paycheck;
import java.math.BigDecimal;

public class Salaried extends AbstractPayType {

  private final BigDecimal salary;

  public Salaried(BigDecimal salary) {
    this.salary = salary;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  @Override
  public BigDecimal calculatePay(Paycheck pc) {
    return salary;
  }
}
