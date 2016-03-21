package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.request.DeleteEmployeePayrollRequest;
import com.georgiev.payroll.request.Request;

public class DeleteEmployeeUseCase implements UseCase {

  @Override
  public void execute(Request request) {
    DeleteEmployeePayrollRequest depr = (DeleteEmployeePayrollRequest) request;
    GpayrollDatabase.deleteEmployee(depr.getEmployeeId());

  }

  @Override
  public Response getResponse() {
    // TODO Auto-generated method stub
    return null;
  }

}
