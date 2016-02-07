package com.georgiev.payroll.transaction.impl;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Affiliation;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.UnionAffiliation;
import java.math.BigDecimal;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

  private final int memberId;
  private final BigDecimal weeklyDues;

  public ChangeMemberTransaction(int employeeId, int memberId, BigDecimal weeklyDues) {
    super(employeeId);
    this.memberId = memberId;
    this.weeklyDues = weeklyDues;
  }

  @Override
  protected Affiliation getAffiliation() {
    return new UnionAffiliation(memberId, weeklyDues);
  }

  @Override
  protected void recordMembership(Employee employee) {
    GpayrollDatabase.addUnionMember(memberId, employee);
  }

}
