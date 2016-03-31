package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.request.Request;

public abstract class ChangePayTypeUseCase extends ChangeEmployeeUseCase {
  public ChangePayTypeUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  public void change(Employee employee, Request request) {
    employee.setPayType(getClassification(request));
    employee.setPaySchedule(getSchedule());
  }

  protected abstract AbstractPayType getClassification(Request request);

  protected abstract PaySchedule getSchedule();

}
