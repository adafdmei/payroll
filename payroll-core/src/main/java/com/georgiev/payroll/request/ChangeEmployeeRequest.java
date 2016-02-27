package com.georgiev.payroll.transaction.impl;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.transaction.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {

  private final int employeeId;

  public ChangeEmployeeTransaction(int employeeId) {
    this.employeeId = employeeId;
  }

  @Override
  public void execute() {
    Employee e = GpayrollDatabase.getEmployee(employeeId);
    if (e != null) {
      change(e);
    }
  }

  protected abstract void change(Employee employee);

}
