package com.georgiev.payroll.transaction.impl;

import com.georgiev.payroll.domain.PaymentClassification;
import com.georgiev.payroll.domain.PaymentSchedule;
import com.georgiev.payroll.impl.MonthlySchedule;
import com.georgiev.payroll.impl.SalariedClassification;
import java.math.BigDecimal;

public class AddSalariedEmployee extends AddEmployeeTransaction {

  private final BigDecimal salary;

  public AddSalariedEmployee(int employeeId, String name, String address, BigDecimal salary) {
    super(employeeId, name, address);
    this.salary = salary;
  }

  @Override
  protected PaymentClassification getClassification() {
    return new SalariedClassification(salary);
  }

  @Override
  protected PaymentSchedule getSchedule() {
    return new MonthlySchedule();
  }

}
