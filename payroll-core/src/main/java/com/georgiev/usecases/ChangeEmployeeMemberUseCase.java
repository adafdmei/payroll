package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.request.ChangeMemberRequest;
import com.georgiev.payroll.request.Request;

public class ChangeEmployeeMemberUseCase extends ChangeEmployeeAffiliationUseCase {

  @Override
  protected UnionMembership getAffiliation(Request request) {
    ChangeMemberRequest cmReq = (ChangeMemberRequest) request;
    return new Member(cmReq.getMemberId(), cmReq.getWeeklyDues());
  }

  @Override
  protected void recordMembership(Employee employee, Request request) {
    ChangeMemberRequest cmReq = (ChangeMemberRequest) request;
    GpayrollDatabase.addUnionMember(cmReq.getMemberId(), employee);
  }
}
