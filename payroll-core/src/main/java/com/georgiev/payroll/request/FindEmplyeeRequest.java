package com.georgiev.payroll.request;

import java.util.Map;

import com.georgiev.util.Constants;

public class FindEmplyeeRequest extends EmployeeRequest {

  public FindEmplyeeRequest(int employeeId) {
    super(employeeId);
  }

  public static Request createFindAnEmployeeRequest(Map<String, Object> dataArgs) {
    int employeeId = (int) dataArgs.get(Constants.ID.name());
    return new FindEmplyeeRequest(employeeId);
  }

}
