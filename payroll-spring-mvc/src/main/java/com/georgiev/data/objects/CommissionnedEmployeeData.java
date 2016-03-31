package com.georgiev.data.objects;

import com.georgiev.util.Constants;

public class CommissionnedEmployeeData extends EmployeeData {

  @Override
  EmployeeData createEmployeeData(EmployeeForm form) {
    if (form.getType().equals("Commissioned")) {
      CommissionnedEmployeeData comData = new CommissionnedEmployeeData();
      initData(form, comData);
      comData.data.put(Constants.BASE_PAY.name(), form.getBasePay());
      comData.data.put(Constants.COMMISSION_RATE.name(), form.getCommissionRate());
      return comData;
    }
    else if (successor != null) {
      return successor.createEmployeeData(form);
    }
    else {
      throw new RuntimeException("The Successor is Not SET");
    }
  }
}
