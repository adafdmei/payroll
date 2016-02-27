package com.georgiev.payroll.request;


public abstract class ChangeEmployeeRequest implements Request {

  private int employeeId;

  public ChangeEmployeeRequest(int employeeId) {
    this.employeeId = employeeId;
  }

  public int getEmployeeId() {
    return employeeId;
  }
}
