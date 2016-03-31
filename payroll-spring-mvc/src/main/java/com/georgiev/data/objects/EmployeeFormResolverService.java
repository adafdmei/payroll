package com.georgiev.data.objects;

import org.springframework.stereotype.Service;

@Service
public class EmployeeFormResolverService {

  EmployeeData salData;
  EmployeeData hrData;
  EmployeeData comData;

  public EmployeeFormResolverService() {
    salData = new SalariedEmployeeData();
    hrData = new HourlyEmployeeData();
    comData = new CommissionnedEmployeeData();
    salData.setSuccessor(hrData);
    hrData.setSuccessor(comData);
  }

  public EmployeeData resolveForm(EmployeeForm form) {
    return salData.createEmployeeData(form);
  }
}
