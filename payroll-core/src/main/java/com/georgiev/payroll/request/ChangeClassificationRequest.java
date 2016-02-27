package com.georgiev.payroll.request;


public abstract class ChangeClassificationRequest extends ChangeEmployeeRequest {

  public ChangeClassificationRequest(int employeeId) {
    super(employeeId);
  }
}
