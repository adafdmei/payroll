package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.request.ChangeMemberRequest;
import com.georgiev.payroll.request.Request;

public class ChangeEmployeeMemberUseCase extends ChangeEmployeeAffiliationUseCase {

  public ChangeEmployeeMemberUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  protected UnionMembership getAffiliation(Request request) {
    ChangeMemberRequest cmReq = (ChangeMemberRequest) request;
    return new Member(cmReq.getMemberId(), cmReq.getWeeklyDues());
  }

  @Override
  protected void recordMembership(Employee employee, Request request) {
    ChangeMemberRequest cmReq = (ChangeMemberRequest) request;
    payrollDatabase.addUnionMember(cmReq.getMemberId(), employee);
  }

}
