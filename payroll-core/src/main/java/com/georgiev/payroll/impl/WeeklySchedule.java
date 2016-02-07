package com.georgiev.payroll.impl;

import static java.util.Calendar.FRIDAY;

import com.georgiev.payroll.domain.PaymentSchedule;
import com.georgiev.util.DateHelper;
import java.util.Date;

public class WeeklySchedule implements PaymentSchedule {

  @Override
  public boolean isPayDay(Date payDate) {
    return DateHelper.dayOfWeek(payDate) == FRIDAY;
  }

  @Override
  public Date getPayPeriodStartDate(Date payDate) {
    return DateHelper.subtractDays(payDate, 6);
  }

}
