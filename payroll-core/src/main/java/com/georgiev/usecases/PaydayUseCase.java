package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.payroll.request.PaydayRequest;
import com.georgiev.payroll.request.Request;
import java.util.Date;
import java.util.List;

public class PaydayUseCase extends AbstractUseCase {

  public PaydayUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  public void execute(Request request) {
    PaydayRequest pdReq = (PaydayRequest) request;
    Date payDate = pdReq.getPayDate();

    List<Integer> empIds = payrollDatabase.getAllEmployeeIds();
    for (int empId : empIds) {
      Employee e = payrollDatabase.getEmployee(empId);
      if (e != null && e.isPayDay(payDate)) {
        Paycheck pc = new Paycheck(e.getPayPeriodStartDate(payDate), payDate);
        e.payDay(pc);
      }
    }
  }
}
