package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.impl.NoMember;
import com.georgiev.payroll.request.Request;

public class ChangeEmployeeNoMemberUseCase extends ChangeEmployeeAffiliationUseCase {

  @Override
  protected UnionMembership getAffiliation(Request request) {
    return new NoMember();
  }

  @Override
  protected void recordMembership(Employee employee, Request request) {
    UnionMembership af = employee.getUnionMembership();
    if (af instanceof Member) {
      Member uf = (Member) af;
      GpayrollDatabase.deleteUnionMember(uf.getMemberId());
    }
  }

  @Override
  public Response getResponse() {
    // TODO Auto-generated method stub
    return null;
  }
}
