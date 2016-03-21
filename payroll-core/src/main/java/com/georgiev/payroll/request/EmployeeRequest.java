package com.georgiev.payroll.request;


public abstract class EmployeeRequest implements Request {

  private int employeeId;

  public EmployeeRequest(int employeeId) {
    this.employeeId = employeeId;
  }

  public int getEmployeeId() {
    return employeeId;
  }
}
