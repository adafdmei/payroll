package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface PayEmployeeRequestBuilder {
  Request buildPayDayRequest(Map<String, Object> dataArg);
}
