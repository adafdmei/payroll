package com.georgiev.payroll.transaction.impl;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.transaction.Transaction;

public class DeleteEmployeeTransaction implements Transaction {

  private final int employeeId;

  public DeleteEmployeeTransaction(int employeeId) {
    this.employeeId = employeeId;
  }

  @Override
  public void execute() {
    GpayrollDatabase.deleteEmployee(employeeId);
  }

}
