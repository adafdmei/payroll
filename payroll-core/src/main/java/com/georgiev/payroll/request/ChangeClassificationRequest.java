package com.georgiev.payroll.request;


public abstract class ChangeClassificationRequest extends EmployeeRequest {

  public ChangeClassificationRequest(int employeeId) {
    super(employeeId);
  }
}
