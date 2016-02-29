package com.georgiev.payroll.request;

import java.util.Map;

import com.georgiev.util.Constants;

public class ChangeNoMemberRequest extends ChangeAffiliationRequest {

  public ChangeNoMemberRequest(int employeeId) {
    super(employeeId);
  }

  public static Request createChangeNoMemberRequest(Map<String, Object> dataArgs) {
    int employeeId = (int) dataArgs.get(Constants.ID.name());
    return new ChangeNoMemberRequest(employeeId);
  }
}
