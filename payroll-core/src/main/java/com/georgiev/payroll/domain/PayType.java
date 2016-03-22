package com.georgiev.payroll.domain;

import java.math.BigDecimal;

public interface PayType {

  BigDecimal calculatePay(Paycheck pc);

}
