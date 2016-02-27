package com.georgiev.payroll.impl;

import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.domain.Paycheck;
import java.math.BigDecimal;

public class NoMember implements UnionMembership {

  @Override
  public BigDecimal calculateDeductions(Paycheck pc) {
    return BigDecimal.valueOf(0.0);
  }

}
