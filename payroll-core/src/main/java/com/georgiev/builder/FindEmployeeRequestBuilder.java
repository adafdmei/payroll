package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface FindEmployeeRequestBuilder {
  Request buildGetAllEmployeeRequest();

  Request buildFindAnEmployeeRequest(Map<String, Object> dataArgs);
}
