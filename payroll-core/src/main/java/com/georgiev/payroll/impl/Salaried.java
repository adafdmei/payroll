package com.georgiev.payroll.impl;

import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.payroll.domain.PaymentClassification;
import java.math.BigDecimal;

public class SalariedClassification extends PaymentClassification {

  private final BigDecimal salary;

  public SalariedClassification(BigDecimal salary) {
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
