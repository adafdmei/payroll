package com.georgiev.payroll.request;

import java.math.BigDecimal;
import java.util.Map;

import com.georgiev.util.Constants;

public class ChangeSalariedRequest extends ChangeClassificationRequest {

  private final BigDecimal salary;

  public ChangeSalariedRequest(int employeeId, BigDecimal salary) {
    super(employeeId);
    this.salary = salary;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public static Request createChangeSalariedRequest(Map<String, Object> dataArgs) {
    int employeeId = (Integer) dataArgs.get(Constants.EMPLOYEE_ID.name());
    BigDecimal salary = (BigDecimal) dataArgs.get(Constants.SALARY.name());
    return new ChangeSalariedRequest(employeeId, salary);
  }

}
