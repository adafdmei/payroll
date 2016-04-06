package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PayType;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.TimeCard;
import com.georgiev.payroll.request.AddTimeCardRequest;
import com.georgiev.payroll.request.Request;

public class AddTimeCardUseCase extends AbstractUseCase {

  public AddTimeCardUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  public void execute(Request request) {
    AddTimeCardRequest tcReq = (AddTimeCardRequest) request;
    TimeCard tc = new TimeCard(tcReq.getDate(), tcReq.getHours());
    //Employee e = payrollDatabase.getEmployee(tcReq.getEmployeeId());
    Employee e = payrollDatabase.getEmployeeWithTimeCards(tcReq.getEmployeeId());

    if (e != null) {
      PayType pc = e.getPayType();
      if (pc instanceof Hourly) {
        Hourly hc = (Hourly) pc;
        hc.addTimeCard(tc);
        //payrollDatabase.addEmployee(e);
        payrollDatabase.sync(e);
        response = new MessageResponse("OK");
      }
      else {
        throw new RuntimeException("Tried to add timecard to non-hourly employee");
      }
    }
    else {
      throw (new RuntimeException("No such employee."));
    }
  }
}
