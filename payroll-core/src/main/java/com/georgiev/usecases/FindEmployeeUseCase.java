package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.Request;
import java.util.Map;

public abstract class FindEmployeeUseCase extends AbstractUseCase {

  public FindEmployeeUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  public void execute(Request request) {
    Map<String, Employee> employees = findEmployee(request);
    response = new EmployeeResponse(employees);
  }

  protected abstract Map<String, Employee> findEmployee(Request request);

}
