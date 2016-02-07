package com.georgiev.payroll.transaction.impl;

import com.georgiev.payroll.domain.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {

  private final String name;

  public ChangeNameTransaction(int employeeId, String name) {
    super(employeeId);
    this.name = name;
  }

  @Override
  protected void change(Employee employee) {
    employee.setName(name);
  }

}
