package com.georgiev.data.objects;

import com.georgiev.util.Constants;

public class SalariedEmployeeData extends EmployeeData {

  public SalariedEmployeeData(EmployeeForm form) {
    super(form);
    data.put(Constants.SALARY.name(), form.getSalary());
  }

}
