package com.georgiev.payroll.domain;

import java.util.Date;

import com.georgiev.util.DateHelper;

public abstract class AbstractPayType implements PayType {

  protected boolean isInPayPeriod(Date date, Paycheck pc) {
    return DateHelper.isBetween(date, pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
  }

}
