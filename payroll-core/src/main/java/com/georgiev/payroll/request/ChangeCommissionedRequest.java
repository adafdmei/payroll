package com.georgiev.payroll.request;

import java.math.BigDecimal;
import java.util.Map;

import com.georgiev.util.Constants;

public class ChangeCommissionedRequest extends ChangeClassificationRequest {

  private final BigDecimal salary;
  private final BigDecimal commissionRate;

  public ChangeCommissionedRequest(int employeeId, BigDecimal salary, BigDecimal commissionRate) {
    super(employeeId);
    this.salary = salary;
    this.commissionRate = commissionRate;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public BigDecimal getCommissionRate() {
    return commissionRate;
  }

  public static Request createChangeCommissionedRequest(Map<String, Object> dataArgs) {
    int employeeId = (int) dataArgs.get(Constants.ID.name());
    BigDecimal basePay = (BigDecimal) dataArgs.get(Constants.BASE_PAY.name());
    BigDecimal commissionRate = (BigDecimal) dataArgs.get(Constants.COMMISSION_RATE.name());
    return new ChangeCommissionedRequest(employeeId, basePay, commissionRate);
  }
}
