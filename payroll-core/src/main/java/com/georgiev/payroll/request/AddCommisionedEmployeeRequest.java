package com.georgiev.payroll.request;

import java.math.BigDecimal;
import java.util.Map;

import com.georgiev.util.Constants;

public class AddCommisionedEmployeeRequest extends AddEmployeeRequest {

  private final BigDecimal basePay;
  private final BigDecimal commissionRate;

  private AddCommisionedEmployeeRequest(Map<String, Object> dataArgs) {
    super(dataArgs);
    basePay = (BigDecimal) dataArgs.get(Constants.BASE_PAY.name());
    commissionRate = (BigDecimal) dataArgs.get(Constants.COMMISSION_RATE.name());
  }

  public static AddCommisionedEmployeeRequest createAddCommisionedEmployeePayrollRequest(Map<String, Object> dataArgs) {
    return new AddCommisionedEmployeeRequest(dataArgs);
  }

  public BigDecimal getBasePay() {
    return basePay;
  }

  public BigDecimal getCommissionRate() {
    return commissionRate;
  }

}
