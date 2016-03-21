package com.georgiev.data.objects;

public class EmployeeDataFactory {

  public static EmployeeData createEmployee(EmployeeForm form) {
    if (form.getType().equals("Salaried")) {
      return new SalariedEmployeeData(form);
    }
    else if (form.getType().equals("Commissioned")) {
      return new CommissionnedEmployeeData(form);
    }
    else {
      return new HourlyEmployeeData(form);
    }
  }

}
