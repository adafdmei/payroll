package com.georgiev.usecases;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.ChangeEmployeeNameRequest;
import com.georgiev.payroll.request.Request;

public class ChangeEmployeeNameUseCase extends ChangeEmployeeUseCase {

  @Override
  public void change(Employee employee,Request request) {
    ChangeEmployeeNameRequest cenReq= (ChangeEmployeeNameRequest)request; 
    employee.setName(cenReq.getName());
  }

}
