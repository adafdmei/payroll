package com.georgiev.data.objects;

import com.georgiev.util.Constants;

public class CommissionnedEmployeeData extends EmployeeData {

  public CommissionnedEmployeeData(EmployeeForm form) {
    super(form);
    data.put(Constants.BASE_PAY.name(), form.getBasePay());
    data.put(Constants.COMMISSION_RATE.name(), form.getCommissionRate());
  }
}
