package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface AddTimeCardRequestBuilder {

  Request buildAddTimeCardRequest(Map<String, Object> data);

}
