package com.georgiev.payroll.request;

import com.georgiev.util.Constants;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class AddTimeCardRequest implements Request {

  private Date date;
  private BigDecimal hours;
  private int employeeId;

  public AddTimeCardRequest(Date date, BigDecimal hours, int employeeId) {
    this.date = date;
    this.hours = hours;
    this.employeeId = employeeId;
  }

  public static AddTimeCardRequest createAddTimeCardRequest(Map<String, Object> dataArgs) {
    Date date = (Date) dataArgs.get(Constants.DATE.name());
    BigDecimal hours = (BigDecimal) dataArgs.get(Constants.HOURS.name());
    int employeeId = (Integer) dataArgs.get(Constants.ID.name());
    return new AddTimeCardRequest(date, hours, employeeId);
  }

  public Date getDate() {
    return date;
  }

  public BigDecimal getHours() {
    return hours;
  }

  public int getEmployeeId() {
    return employeeId;
  }
}
