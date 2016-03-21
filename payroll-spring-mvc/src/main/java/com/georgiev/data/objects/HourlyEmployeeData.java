package com.georgiev.data.objects;

import com.georgiev.util.Constants;

public class HourlyEmployeeData extends EmployeeData {

  public HourlyEmployeeData(EmployeeForm form) {
    super(form);
    data.put(Constants.HOURLY_RATE.name(), form.getHourlyRate());

  }
}
