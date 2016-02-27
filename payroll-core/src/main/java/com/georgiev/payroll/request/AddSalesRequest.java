package com.georgiev.payroll.request;

import com.georgiev.util.Constants;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class AddSalesRequest implements Request {

  private Date date;
  private BigDecimal amount;
  private int employeeId;

  public AddSalesRequest(Date date, BigDecimal amount, int employeeId) {
    this.date = date;
    this.amount = amount;
    this.employeeId = employeeId;
  }

  public Date getDate() {
    return date;
  } 

  public BigDecimal getAmount() {
    return amount;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public static Request createAddSalesRecieptRequest(Map<String, Object> dataArgs) {
    Date date = (Date) dataArgs.get(Constants.DATE.name());
    BigDecimal amount = (BigDecimal) dataArgs.get(Constants.SOLD_AMOUNT.name());
    int employeeId = (Integer) dataArgs.get(Constants.EMPLOYEE_ID.name());
    return new AddSalesRequest(date, amount, employeeId);
  }
}
