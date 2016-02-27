package com.georgiev.payroll.request;

import java.util.Map;

import com.georgiev.util.Constants;

public abstract class AddEmployeeRequest implements Request {
  private final int employeeId;
  private final String name;
  private final String address;

  public AddEmployeeRequest(Map<String, Object> dataArgs) {
    address = (String) dataArgs.get(Constants.ADDRESS.name());
    employeeId = (Integer) dataArgs.get(Constants.EMPLOYEE_ID.name());
    name = (String) dataArgs.get(Constants.NAME.name());
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }
}
