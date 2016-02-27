package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface DeleteEmployeeRequestBuilder {

  Request buildDeleteEmployeeRequest(Map<String, Object> data);
}