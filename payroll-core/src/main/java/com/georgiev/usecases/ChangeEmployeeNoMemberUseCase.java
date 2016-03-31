package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.impl.NoMember;
import com.georgiev.payroll.request.Request;

public class ChangeEmployeeNoMemberUseCase extends ChangeEmployeeAffiliationUseCase {

  public ChangeEmployeeNoMemberUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  protected UnionMembership getAffiliation(Request request) {
    return new NoMember();
  }

  @Override
  protected void recordMembership(Employee employee, Request request) {
    UnionMembership af = employee.getUnionMembership();
    if (af instanceof Member) {
      Member uf = (Member) af;
      payrollDatabase.deleteUnionMember(uf.getMemberId());
    }
  }
}
