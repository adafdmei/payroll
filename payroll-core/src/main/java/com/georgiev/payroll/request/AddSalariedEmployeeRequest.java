package com.georgiev.payroll.request;

import java.math.BigDecimal;
import java.util.Map;

import com.georgiev.util.Constants;

public class AddSalariedEmployeeRequest extends AddEmployeeRequest {

  private final BigDecimal salary;

  private AddSalariedEmployeeRequest(Map<String, Object> dataArgs) {
    super(dataArgs);
    salary = (BigDecimal) dataArgs.get(Constants.SALARY.name());
  }

  public static AddSalariedEmployeeRequest createAddSalariedEmployeePayrollRequest(Map<String, Object> dataArgs) {
    return new AddSalariedEmployeeRequest(dataArgs);
  }

  public BigDecimal getSalary() {
    return salary;
  }
}
