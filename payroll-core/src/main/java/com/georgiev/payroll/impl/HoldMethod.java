package com.georgiev.payroll.impl;

import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.payroll.domain.PaymentMethod;

public class HoldMethod implements PaymentMethod {

  @Override
  public void pay(Paycheck pc) {
    pc.setField("Disposition", "Hold");
  }

}
