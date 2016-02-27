package com.georgiev.payroll.request;

import java.math.BigDecimal;
import java.util.Map;

import com.georgiev.util.Constants;

public class ChangeHourlyRequest extends ChangeClassificationRequest {

  private final BigDecimal hourlyRate;

  public ChangeHourlyRequest(int employeeId, BigDecimal hourlyRate) {
    super(employeeId);
    this.hourlyRate = hourlyRate;
  }

  public BigDecimal getHourlyRate() {
    return hourlyRate;
  }

  public static Request createChangeHourlyRequest(Map<String, Object> dataArgs) {
    int employeeId = (Integer) dataArgs.get(Constants.EMPLOYEE_ID.name());
    BigDecimal hourlyRate = (BigDecimal) dataArgs.get(Constants.HOURLY_RATE.name());
    return new ChangeHourlyRequest(employeeId, hourlyRate);
  }

}
