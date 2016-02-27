package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface AddServiceChargeRequestBuilder {

  Request buildAddServiceChargeRequest(Map<String, Object> dataArgs);

}