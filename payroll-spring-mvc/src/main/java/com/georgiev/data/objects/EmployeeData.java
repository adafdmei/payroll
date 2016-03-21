package com.georgiev.data.objects;

import java.util.HashMap;
import java.util.Map;

import com.georgiev.util.Constants;

public abstract class EmployeeData {

  protected Map<String, Object> data;

  public EmployeeData(EmployeeForm form) {
    data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), form.getId());
    data.put(Constants.NAME.name(), form.getName());
    data.put(Constants.ADDRESS.name(), form.getAddress());
  }

  public Map<String, Object> getDataAsMap() {
    return data;
  }
}
