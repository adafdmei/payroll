package com.georgiev.payroll.impl;

import com.georgiev.payroll.domain.Affiliation;
import com.georgiev.payroll.domain.Paycheck;
import java.math.BigDecimal;

public class NoAffiliation implements Affiliation {

  @Override
  public BigDecimal calculateDeductions(Paycheck pc) {
    return BigDecimal.valueOf(0.0);
  }

}
