package com.georgiev.payroll.request;

import java.math.BigDecimal;
import java.util.Map;

import com.georgiev.util.Constants;

public class AddHourlyEmployeeRequest extends AddEmployeeRequest {

  private final BigDecimal hourlyRate;

  private AddHourlyEmployeeRequest(Map<String, Object> dataArgs) {
    super(dataArgs);
    hourlyRate = (BigDecimal) dataArgs.get(Constants.HOURLY_RATE.name());
  }

  public static AddHourlyEmployeeRequest createAddHourlyEmployeePayrollRequest(Map<String, Object> dataArgs) {
    return new AddHourlyEmployeeRequest(dataArgs);
  }

  public BigDecimal getHourlyRate() {
    return hourlyRate;
  }

}
