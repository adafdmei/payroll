package com.georgiev.payroll.transaction.impl;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Affiliation;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.ServiceCharge;
import com.georgiev.payroll.impl.UnionAffiliation;
import com.georgiev.payroll.transaction.Transaction;
import java.math.BigDecimal;
import java.util.Date;

public class ServiceChargeTransaction implements Transaction {

  private final int memberId;
  private final Date date;
  private final BigDecimal charge;

  public ServiceChargeTransaction(int memberId, Date date, BigDecimal charge) {
    this.memberId = memberId;
    this.date = date;
    this.charge = charge;
  }

  @Override
  public void execute() {
    Employee e = GpayrollDatabase.getUnionMember(memberId);
    Affiliation af = e.getAffiliation();
    if (af instanceof UnionAffiliation) {
      UnionAffiliation uaf = (UnionAffiliation) af;
      uaf.addServiceCharge(new ServiceCharge(date, charge));
    }
  }

}
