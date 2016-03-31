package com.georgiev.data.objects;

import com.georgiev.util.Constants;

public class HourlyEmployeeData extends EmployeeData {

  @Override
  EmployeeData createEmployeeData(EmployeeForm form) {
    if (form.getType().equals("Hourly")) {
      HourlyEmployeeData hrData = new HourlyEmployeeData();
      initData(form, hrData);
      hrData.data.put(Constants.HOURLY_RATE.name(), form.getHourlyRate());
      return hrData;
    }
    else if (successor != null) {
      return successor.createEmployeeData(form);
    }
    else {
      throw new RuntimeException("The Successor is Not SET");
    }
  }
}
