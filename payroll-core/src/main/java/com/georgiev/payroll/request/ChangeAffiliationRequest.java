package com.georgiev.payroll.request;


public abstract class ChangeAffiliationRequest extends ChangeEmployeeRequest {

  public ChangeAffiliationRequest(int employeeId) {
    super(employeeId);
  }
}