package com.georgiev.web.controller;

import java.util.HashMap;
import java.util.Map;

import com.georgiev.util.Constants;

public class PrepareDataForPayrollRequest {

  private final Employee employee;

  public PrepareDataForPayrollRequest(Employee employee) {
    this.employee = employee;
  }

  public Map<String, Object> prepareDataForHourly() {
    Map<String, Object> data = initEmployee();
    data.put(Constants.HOURLY_RATE.name(), employee.getHourlyRate());

    return data;
  }

  private Map<String, Object> initEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), employee.getId());
    data.put(Constants.NAME.name(), employee.getName());
    data.put(Constants.ADDRESS.name(), employee.getAddress());
    return data;
  }

  public Map<String, Object> prepareDataForSalaried() {
    Map<String, Object> data = initEmployee();
    data.put(Constants.SALARY.name(), employee.getSalary());

    return data;
  }

  public Map<String, Object> prepareDataForCommissioned() {
    Map<String, Object> data = initEmployee();
    data.put(Constants.BASE_PAY.name(), employee.getBasePay());
    data.put(Constants.COMMISSION_RATE.name(), employee.getCommissionRate());

    return data;
  }
}
