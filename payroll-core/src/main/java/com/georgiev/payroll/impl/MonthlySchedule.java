package com.georgiev.payroll.impl;

import static java.util.Calendar.DAY_OF_MONTH;

import com.georgiev.payroll.domain.PaymentSchedule;
import java.util.Calendar;
import java.util.Date;

public class MonthlySchedule implements PaymentSchedule {

  @Override
  public boolean isPayDay(Date payDate) {
    return isLastDayOfMonth(payDate);
  }

  private boolean isLastDayOfMonth(Date payDate) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(payDate);
    return cal.get(DAY_OF_MONTH) == cal.getActualMaximum(DAY_OF_MONTH);
  }

  @Override
  public Date getPayPeriodStartDate(Date payDate) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(payDate);
    cal.set(DAY_OF_MONTH, 1);
    return cal.getTime();
  }

}
