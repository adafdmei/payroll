package com.georgiev.payroll.impl;

import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.payroll.domain.PayDisposition;

public class HoldMethod implements PayDisposition {

  @Override
  public void pay(Paycheck pc) {
    pc.setField("Disposition", "Hold");
  }
}
