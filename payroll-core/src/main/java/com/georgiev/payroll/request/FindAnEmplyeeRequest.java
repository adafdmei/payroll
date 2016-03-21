package com.georgiev.payroll.request;

import java.util.Map;

import com.georgiev.util.Constants;

public class FindAnEmplyeeRequest extends EmployeeRequest {

  public FindAnEmplyeeRequest(int employeeId) {
    super(employeeId);
  }

  public static Request createFindAnEmployeeRequest(Map<String, Object> dataArgs) {
    int employeeId = (int) dataArgs.get(Constants.ID.name());
    return new FindAnEmplyeeRequest(employeeId);
  }

}
