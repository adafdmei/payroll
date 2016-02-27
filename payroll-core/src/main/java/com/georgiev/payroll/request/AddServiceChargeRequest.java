package com.georgiev.payroll.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.georgiev.util.Constants;

public class AddServiceChargeRequest implements Request {

  private final int memberId;
  private final Date date;
  private final BigDecimal charge;

  public AddServiceChargeRequest(int memberId, Date date, BigDecimal charge) {
    this.memberId = memberId;
    this.date = date;
    this.charge = charge;
  }

  public int getMemberId() {
    return memberId;
  }

  public Date getDate() {
    return date;
  }

  public BigDecimal getCharge() {
    return charge;
  }

  public static Request createServiceChargeRequest(Map<String, Object> dataArgs) {
    Date date = (Date) dataArgs.get(Constants.DATE.name());
    int memberId = (int) dataArgs.get(Constants.MEMBER_ID.name());
    BigDecimal charge = (BigDecimal) dataArgs.get(Constants.CHARGE.name());

    return new AddServiceChargeRequest(memberId, date, charge);
  }
}
