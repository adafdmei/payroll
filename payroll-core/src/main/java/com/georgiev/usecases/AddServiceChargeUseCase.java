package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.ServiceCharge;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.request.AddServiceChargeRequest;
import com.georgiev.payroll.request.Request;

public class AddServiceChargeUseCase implements UseCase {

  @Override
  public void execute(Request request) {
    AddServiceChargeRequest scReq = (AddServiceChargeRequest) request;
    Employee e = GpayrollDatabase.getUnionMember(scReq.getMemberId());
    UnionMembership af = e.getUnionMembership();
    if (af instanceof Member) {
      Member uaf = (Member) af;
      uaf.addServiceCharge(new ServiceCharge(scReq.getDate(), scReq.getCharge()));
    }
  }

}
