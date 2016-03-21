package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import java.util.Date;
import java.util.List;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.payroll.request.PaydayRequest;
import com.georgiev.payroll.request.Request;

public class PaydayUseCase implements UseCase {

  @Override
  public void execute(Request request) {
    PaydayRequest pdReq = (PaydayRequest) request;
    Date payDate = pdReq.getPayDate();

    List<Integer> empIds = GpayrollDatabase.getAllEmployeeIds();
    for (int empId : empIds) {
      Employee e = GpayrollDatabase.getEmployee(empId);
      if (e != null && e.isPayDay(payDate)) {
        Paycheck pc = new Paycheck(e.getPayPeriodStartDate(payDate), payDate);
        e.payDay(pc);
      }
    }
  }

  @Override
  public Response getResponse() {
    // TODO Auto-generated method stub
    return null;
  }
}
