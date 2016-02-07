package com.georgiev.payroll.transaction.impl;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PaymentClassification;
import com.georgiev.payroll.domain.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

  public ChangeClassificationTransaction(int employeeId) {
    super(employeeId);
  }

  @Override
  protected void change(Employee employee) {
    employee.setClassification(getClassification());
    employee.setSchedule(getSchedule());
  }

  protected abstract PaymentClassification getClassification();

  protected abstract PaymentSchedule getSchedule();

}
