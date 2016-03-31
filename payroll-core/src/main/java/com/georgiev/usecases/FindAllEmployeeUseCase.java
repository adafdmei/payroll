package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.Request;
import java.util.Map;

public class FindAllEmployeeUseCase extends FindEmployeeUseCase {

  public FindAllEmployeeUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  protected Map<String, Employee> findEmployee(Request request) {
    return payrollDatabase.getAllEmployees();
  }

}
