package com.georgiev.payroll.transaction.impl;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PaymentClassification;
import com.georgiev.payroll.impl.HourlyClassification;
import com.georgiev.payroll.impl.TimeCard;
import com.georgiev.payroll.transaction.Transaction;
import java.math.BigDecimal;
import java.util.Date;

public class TimeCardTransaction implements Transaction {

  private final Date date;
  private final BigDecimal hours;
  private final int employeeId;

  public TimeCardTransaction(Date date, BigDecimal hours, int employeeId) {
    this.date = date;
    this.hours = hours;
    this.employeeId = employeeId;
  }

  @Override
  public void execute() {
    Employee e = GpayrollDatabase.getEmployee(employeeId);
    if (e != null) {
      PaymentClassification pc = e.getClassification();
      if (pc instanceof HourlyClassification) {
        HourlyClassification hc = (HourlyClassification) pc;
        hc.addTimeCard(new TimeCard(date, hours));
      }
      else {
        throw new RuntimeException("Tried to add timecard to non-hourly employee");
      }
    }
    else {
      throw (new RuntimeException("No such employee."));
    }
  }

}
