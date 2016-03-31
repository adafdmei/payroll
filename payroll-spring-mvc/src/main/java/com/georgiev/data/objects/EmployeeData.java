package com.georgiev.data.objects;

import com.georgiev.util.Constants;
import java.util.HashMap;
import java.util.Map;

public abstract class EmployeeData {

  protected EmployeeData successor;
  protected Map<String, Object> data;

  public Map<String, Object> getDataAsMap() {
    return data;
  }

  public void setSuccessor(EmployeeData successor) {
    this.successor = successor;
  }

  protected void initData(EmployeeForm form, EmployeeData employeeData) {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), form.getId());
    data.put(Constants.NAME.name(), form.getName());
    data.put(Constants.ADDRESS.name(), form.getAddress());
    employeeData.data = data;
  }

  abstract EmployeeData createEmployeeData(EmployeeForm form);

}