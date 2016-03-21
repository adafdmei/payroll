package com.georgiev.utils;

import java.util.Map;

import com.georgiev.data.objects.EmployeeForm;

public class ConvertEmployeeUtils {

  public static Map<String, Object> convertEmployeeDoToData(EmployeeForm employee) {
    PrepareDataForPayrollRequest prepDataForReq = new PrepareDataForPayrollRequest(employee);
    String type = employee.getType();
    if (type.equals("salaried")) {
      return prepDataForReq.prepareDataForSalaried();
    }
    else if (type.equals("commissioned")) {
      return prepDataForReq.prepareDataForCommissioned();
    }
    else {
      return prepDataForReq.prepareDataForHourly();
    }
  }

}