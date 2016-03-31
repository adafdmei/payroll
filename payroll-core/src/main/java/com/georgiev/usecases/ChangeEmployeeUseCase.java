package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.EmployeeRequest;
import com.georgiev.payroll.request.Request;

public abstract class ChangeEmployeeUseCase extends AbstractUseCase {

  public ChangeEmployeeUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  public void execute(Request request) {
    EmployeeRequest ceRec = (EmployeeRequest) request;
    Employee e = payrollDatabase.getEmployee(ceRec.getEmployeeId());
    if (e != null) {
      change(e, request);
    }
  }

  public abstract void change(Employee employee, Request request);

}
