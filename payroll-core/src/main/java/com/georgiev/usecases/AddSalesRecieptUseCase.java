package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PayType;
import com.georgiev.payroll.impl.Commissioned;
import com.georgiev.payroll.impl.SalesReceipt;
import com.georgiev.payroll.request.AddSalesRequest;
import com.georgiev.payroll.request.Request;

public class AddSalesRecieptUseCase implements UseCase {

  @Override
  public void execute(Request request) {
    AddSalesRequest srReq = (AddSalesRequest) request;
    Employee e = GpayrollDatabase.getEmployee(srReq.getEmployeeId());
    if (e != null) {
      PayType pc = e.getPayType();
      if (pc instanceof Commissioned) {
        Commissioned cc = (Commissioned) pc;
        cc.addSalesReceipt(new SalesReceipt(srReq.getDate(), srReq.getAmount()));
      }
      else {
        throw new RuntimeException("Tried to add sales receipt to non-commissioned employee");
      }
    }
    else {
      throw (new RuntimeException("No such employee."));
    }
  }

}
