package com.georgiev.payroll.request;

import java.util.Map;

import com.georgiev.util.Constants;

public class DeleteEmployeePayrollRequest implements Request {

  private int employeeId;

  public DeleteEmployeePayrollRequest(Map<String, Object> dataArgs) {
    employeeId = (Integer) dataArgs.get(Constants.ID.name());
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public static Request createDeleteEmployeePayrollRequest(Map<String, Object> dataArgs) {
    return new DeleteEmployeePayrollRequest(dataArgs);
  }

}
