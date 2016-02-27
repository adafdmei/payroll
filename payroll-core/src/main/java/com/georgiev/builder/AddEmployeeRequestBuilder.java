package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface AddEmployeeRequestBuilder {

  Request buildCommissionedEmployeeRequest(Map<String, Object> dataArgs);

  Request buildSalariedEmployeeRequest(Map<String, Object> dataArgs);

  Request buildHourlyEmployeeRequest(Map<String, Object> dataArgs);

}