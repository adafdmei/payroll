package com.georgiev.builder;

import java.util.Map;

import com.georgiev.payroll.request.Request;

public interface ChangeEmployeeRequestBuilder {

  Request buildChangeEmployeeNameRequest(Map<String, Object> dataArgs);

  Request buildChangeEmployeeHourlyRequest(Map<String, Object> dataArgs);

  Request buildChangeEmployeeSalariedRequest(Map<String, Object> dataArgs);

  Request buildChangeEmployeeCommissionedRequest(Map<String, Object> dataArgs);

  Request buildChangeMemberRequest(Map<String, Object> dataArgs);

  Request buildChangeNoMemberRequest(Map<String, Object> dataArgs);

}