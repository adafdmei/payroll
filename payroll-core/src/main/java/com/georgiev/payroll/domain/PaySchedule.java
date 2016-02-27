package com.georgiev.payroll.domain;

import java.util.Date;

public interface PaySchedule {

  boolean isPayDay(Date payDate);

  Date getPayPeriodStartDate(Date payDate);

}
