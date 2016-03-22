package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface FindEmployeeRequestBuilder {
  Request buildFindAllEmployeesRequest();

  Request buildFindEmployeeRequest(Map<String, Object> dataArgs);
}
