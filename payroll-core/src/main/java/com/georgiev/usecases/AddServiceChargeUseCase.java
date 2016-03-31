package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.impl.ServiceCharge;
import com.georgiev.payroll.request.AddServiceChargeRequest;
import com.georgiev.payroll.request.Request;

public class AddServiceChargeUseCase extends AbstractUseCase {

  public AddServiceChargeUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  public void execute(Request request) {
    AddServiceChargeRequest scReq = (AddServiceChargeRequest) request;
    Employee e = payrollDatabase.getUnionMember(scReq.getMemberId());
    UnionMembership af = e.getUnionMembership();
    if (af instanceof Member) {
      Member uaf = (Member) af;
      uaf.addServiceCharge(new ServiceCharge(scReq.getDate(), scReq.getCharge()));
    }
  }
}
