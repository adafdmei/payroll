package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.FindEmplyeeRequest;
import com.georgiev.payroll.request.Request;
import java.util.HashMap;
import java.util.Map;

public class FindAnEmployeeUseCase extends FindEmployeeUseCase {

  public FindAnEmployeeUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  protected Map<String, Employee> findEmployee(Request request) {
    FindEmplyeeRequest feReq = (FindEmplyeeRequest) request;
    Employee employee = payrollDatabase.getEmployee(feReq.getEmployeeId());
    Map<String, Employee> employeeMap = new HashMap<String, Employee>();
    employeeMap.put(String.valueOf(employee.getEmployeeId()), employee);
    return employeeMap;
  }
}
