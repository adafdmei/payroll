package com.georgiev.usecases;

import java.util.HashMap;
import java.util.Map;

import com.georgiev.payroll.domain.Employee;

public class EmployeeResponse implements Response {

  private final Map<String, Object> employees;

  public EmployeeResponse(Employee employee) {
    employees = new HashMap<String, Object>();
    employees.put(String.valueOf(employee.getEmployeeId()), employee);
  }

  public EmployeeResponse(Map<String, ? extends Object> employees) {
    this.employees = (Map<String, Object>) employees;
  }

  @Override
  public String getAsString() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, Object> getAsMap() {
    return employees;
  }

}
