package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import java.util.HashMap;
import java.util.Map;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.FindAnEmplyeeRequest;
import com.georgiev.payroll.request.Request;

public class FindAnEmployeeUseCase extends GetEmployeeUseCase {

  @Override
  protected Map<String, Employee> getEmployee(Request request) {
    FindAnEmplyeeRequest feReq = (FindAnEmplyeeRequest) request;
    Employee employee = GpayrollDatabase.getEmployee(feReq.getEmployeeId());
    Map<String, Employee> employeeMap = new HashMap<String, Employee>();
    employeeMap.put(String.valueOf(employee.getEmployeeId()), employee);
    return employeeMap;
  }
}
