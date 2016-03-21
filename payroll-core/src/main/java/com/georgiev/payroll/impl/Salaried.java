package com.georgiev.payroll.impl;

import java.math.BigDecimal;

import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.util.Constants;

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

  @Override
  public String getType() {
    return Constants.SALARIED.name();
  }

}
