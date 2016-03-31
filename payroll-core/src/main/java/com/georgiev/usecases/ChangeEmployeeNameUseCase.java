package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.ChangeEmployeeNameRequest;
import com.georgiev.payroll.request.Request;

public class ChangeEmployeeNameUseCase extends ChangeEmployeeUseCase {

  public ChangeEmployeeNameUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  public void change(Employee employee, Request request) {
    ChangeEmployeeNameRequest cenReq = (ChangeEmployeeNameRequest) request;
    employee.setName(cenReq.getName());
  }

}
