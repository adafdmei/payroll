package com.georgiev.payroll.request;

import java.util.Date;
import java.util.Map;

import com.georgiev.util.Constants;

public class PaydayRequest implements Request {

  private final Date payDate;

  public PaydayRequest(Date payDate) {
    this.payDate = payDate;
  }

  public Date getPayDate() {
    return payDate;
  }

  public static Request createPaydayRequest(Map<String, Object> dataArgs) {
    Date payDate = (Date) dataArgs.get(Constants.PAY_DATE.name());
    return new PaydayRequest(payDate);
  }

}
