package com.georgiev.payroll.request;

import java.math.BigDecimal;

public interface PayrollRequest {
  int getId();

  String getName();

  String getAddress();

  BigDecimal getSalary();

  BigDecimal getCommissionRate();
}
