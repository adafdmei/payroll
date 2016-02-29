package com.georgiev.payroll.request;

import java.math.BigDecimal;
import java.util.Map;

import com.georgiev.util.Constants;

public class ChangeMemberRequest extends ChangeAffiliationRequest {

  private final int memberId;
  private final BigDecimal weeklyDues;

  public ChangeMemberRequest(int employeeId, int memberId, BigDecimal weeklyDues) {
    super(employeeId);
    this.memberId = memberId;
    this.weeklyDues = weeklyDues;
  }

  public int getMemberId() {
    return memberId;
  }

  public BigDecimal getWeeklyDues() {
    return weeklyDues;
  }

  public static Request createChangeMemberRequest(Map<String, Object> dataArgs) {
    int employeeId = (Integer) dataArgs.get(Constants.ID.name());
    int memberId = (Integer) dataArgs.get(Constants.MEMBER_ID.name());
    BigDecimal weeklyDues = (BigDecimal) dataArgs.get(Constants.WEEKLY_DUES.name());

    return new ChangeMemberRequest(employeeId, memberId, weeklyDues);
  }
}
