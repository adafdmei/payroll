package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.ChangeEmployeeRequest;
import com.georgiev.payroll.request.Request;

public abstract class ChangeEmployeeUseCase implements UseCase {

  @Override
  public void execute(Request request) {
    ChangeEmployeeRequest ceRec = (ChangeEmployeeRequest) request;
    Employee e = GpayrollDatabase.getEmployee(ceRec.getEmployeeId());
    if (e != null) {
      change(e,request);
    }
  }

  public abstract void change(Employee employee, Request request);

}
