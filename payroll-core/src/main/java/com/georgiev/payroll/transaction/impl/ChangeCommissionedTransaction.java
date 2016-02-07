package com.georgiev.payroll.transaction.impl;

import com.georgiev.payroll.domain.PaymentClassification;
import com.georgiev.payroll.domain.PaymentSchedule;
import com.georgiev.payroll.impl.BiweeklySchedule;
import com.georgiev.payroll.impl.CommissionedClassification;
import java.math.BigDecimal;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {

  private final BigDecimal salary;
  private final BigDecimal commissionRate;

  public ChangeCommissionedTransaction(int employeeId, BigDecimal salary, BigDecimal commissionRate) {
    super(employeeId);
    this.salary = salary;
    this.commissionRate = commissionRate;
  }

  @Override
  protected PaymentClassification getClassification() {
    return new CommissionedClassification(salary, commissionRate);
  }

  @Override
  protected PaymentSchedule getSchedule() {
    return new BiweeklySchedule();
  }

}
