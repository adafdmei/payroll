package com.georgiev.usecases;

import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.Request;

public abstract class ChangeEmployeeAffiliationUseCase extends ChangeEmployeeUseCase {

  @Override
  public void change(Employee employee, Request request) {
    recordMembership(employee, request);
    employee.setUnionMembership(getAffiliation(request));
  }

  protected abstract void recordMembership(Employee employee, Request request);

  protected abstract UnionMembership getAffiliation(Request request);
}