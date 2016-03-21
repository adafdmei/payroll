package com.georgiev.builder.impl;

import java.util.Map;

import com.georgiev.builder.RequestBuilder;
import com.georgiev.payroll.request.AddCommisionedEmployeeRequest;
import com.georgiev.payroll.request.AddHourlyEmployeeRequest;
import com.georgiev.payroll.request.AddSalariedEmployeeRequest;
import com.georgiev.payroll.request.AddSalesRequest;
import com.georgiev.payroll.request.AddServiceChargeRequest;
import com.georgiev.payroll.request.AddTimeCardRequest;
import com.georgiev.payroll.request.ChangeCommissionedRequest;
import com.georgiev.payroll.request.ChangeEmployeeNameRequest;
import com.georgiev.payroll.request.ChangeHourlyRequest;
import com.georgiev.payroll.request.ChangeMemberRequest;
import com.georgiev.payroll.request.ChangeNoMemberRequest;
import com.georgiev.payroll.request.ChangeSalariedRequest;
import com.georgiev.payroll.request.DeleteEmployeePayrollRequest;
import com.georgiev.payroll.request.FindAnEmplyeeRequest;
import com.georgiev.payroll.request.NullRequest;
import com.georgiev.payroll.request.PaydayRequest;
import com.georgiev.payroll.request.Request;

public class RequestBuilderImpl implements RequestBuilder {

  @Override
  public Request buildCommissionedEmployeeRequest(Map<String, Object> dataArgs) {
    return AddCommisionedEmployeeRequest.createAddCommisionedEmployeePayrollRequest(dataArgs);
  }

  @Override
  public Request buildSalariedEmployeeRequest(Map<String, Object> dataArgs) {
    return AddSalariedEmployeeRequest.createAddSalariedEmployeePayrollRequest(dataArgs);
  }

  @Override
  public Request buildHourlyEmployeeRequest(Map<String, Object> dataArgs) {
    return AddHourlyEmployeeRequest.createAddHourlyEmployeePayrollRequest(dataArgs);
  }

  @Override
  public Request buildDeleteEmployeeRequest(Map<String, Object> dataArgs) {
    return DeleteEmployeePayrollRequest.createDeleteEmployeePayrollRequest(dataArgs);
  }

  @Override
  public Request buildAddTimeCardRequest(Map<String, Object> dataArgs) {
    return AddTimeCardRequest.createAddTimeCardRequest(dataArgs);
  }

  @Override
  public Request buildAddSalesRecieptRequest(Map<String, Object> dataArgs) {
    return AddSalesRequest.createAddSalesRecieptRequest(dataArgs);
  }

  @Override
  public Request buildChangeEmployeeNameRequest(Map<String, Object> dataArgs) {
    return ChangeEmployeeNameRequest.createChangeEmployeeNameRequest(dataArgs);
  }

  @Override
  public Request buildChangeEmployeeHourlyRequest(Map<String, Object> dataArgs) {
    return ChangeHourlyRequest.createChangeHourlyRequest(dataArgs);
  }

  @Override
  public Request buildChangeEmployeeSalariedRequest(Map<String, Object> dataArgs) {
    return ChangeSalariedRequest.createChangeSalariedRequest(dataArgs);
  }

  @Override
  public Request buildChangeEmployeeCommissionedRequest(Map<String, Object> dataArgs) {
    return ChangeCommissionedRequest.createChangeCommissionedRequest(dataArgs);
  }

  @Override
  public Request buildChangeMemberRequest(Map<String, Object> dataArgs) {
    return ChangeMemberRequest.createChangeMemberRequest(dataArgs);
  }

  @Override
  public Request buildChangeNoMemberRequest(Map<String, Object> dataArgs) {
    return ChangeNoMemberRequest.createChangeNoMemberRequest(dataArgs);
  }

  @Override
  public Request buildAddServiceChargeRequest(Map<String, Object> dataArgs) {
    return AddServiceChargeRequest.createServiceChargeRequest(dataArgs);
  }

  @Override
  public Request buildPayDayRequest(Map<String, Object> dataArgs) {
    return PaydayRequest.createPaydayRequest(dataArgs);
  }

  @Override
  public Request buildGetAllEmployeeRequest() {
    return new NullRequest();
  }

  @Override
  public Request buildFindAnEmployeeRequest(Map<String, Object> dataArgs) {
    return FindAnEmplyeeRequest.createFindAnEmployeeRequest(dataArgs);
  }

}
