package com.georgiev.payroll.domain;

import java.math.BigDecimal;

public interface UnionMembership {

  BigDecimal calculateDeductions(Paycheck pc);

}
