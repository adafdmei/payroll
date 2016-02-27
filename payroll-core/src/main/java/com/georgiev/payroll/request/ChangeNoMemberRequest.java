package com.georgiev.payroll.transaction.impl;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Affiliation;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.NoAffiliation;
import com.georgiev.payroll.impl.UnionAffiliation;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {

  public ChangeUnaffiliatedTransaction(int employeeId) {
    super(employeeId);
  }

  @Override
  protected Affiliation getAffiliation() {
    return new NoAffiliation();
  }

  @Override
  protected void recordMembership(Employee employee) {
    Affiliation af = employee.getAffiliation();
    if (af instanceof UnionAffiliation) {
      UnionAffiliation uf = (UnionAffiliation) af;
      GpayrollDatabase.deleteUnionMember(uf.getMemberId());
    }
  }

}
