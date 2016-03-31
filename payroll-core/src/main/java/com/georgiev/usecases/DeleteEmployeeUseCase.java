package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.DeleteEmployeePayrollRequest;
import com.georgiev.payroll.request.Request;

public class DeleteEmployeeUseCase extends AbstractUseCase {

  public DeleteEmployeeUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  public void execute(Request request) {
    DeleteEmployeePayrollRequest depr = (DeleteEmployeePayrollRequest) request;
    payrollDatabase.deleteEmployee(depr.getEmployeeId());

  }
}
