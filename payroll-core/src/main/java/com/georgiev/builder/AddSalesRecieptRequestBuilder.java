package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface AddSalesRecieptRequestBuilder {

  Request buildAddSalesRecieptRequest(Map<String, Object> data);

}
