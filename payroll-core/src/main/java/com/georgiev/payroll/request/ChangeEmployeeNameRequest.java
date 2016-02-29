package com.georgiev.payroll.request;

import java.util.Map;

import com.georgiev.util.Constants;

public class ChangeEmployeeNameRequest extends ChangeEmployeeRequest {

  private String name;

  public ChangeEmployeeNameRequest(int employeeId, String name) {
    super(employeeId);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static Request createChangeEmployeeNameRequest(Map<String, Object> dataArgs) {
    int employeeId = (int) dataArgs.get(Constants.ID.name());
    String name = (String) dataArgs.get(Constants.NAME.name());
    return new ChangeEmployeeNameRequest(employeeId, name);
  }
}
