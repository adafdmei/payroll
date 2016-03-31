package com.georgiev.data.objects;

import com.georgiev.util.Constants;

public class SalariedEmployeeData extends EmployeeData {

  @Override
  EmployeeData createEmployeeData(EmployeeForm form) {
    if (form.getType().equals("Salaried")) {
      SalariedEmployeeData salData = new SalariedEmployeeData();
      initData(form, salData);
      salData.data.put(Constants.SALARY.name(), form.getSalary());
      return salData;
    }
    else if (successor != null) {
      return successor.createEmployeeData(form);
    }
    else {
      throw new RuntimeException("The Successor is Not SET");
    }
  }
}
