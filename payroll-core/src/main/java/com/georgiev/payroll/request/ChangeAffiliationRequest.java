package com.georgiev.payroll.request;


public abstract class ChangeAffiliationRequest extends EmployeeRequest {

  public ChangeAffiliationRequest(int employeeId) {
    super(employeeId);
  }
}