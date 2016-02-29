package com.georgiev.usecases;

import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.impl.MonthlySchedule;
import com.georgiev.payroll.impl.Salaried;
import com.georgiev.payroll.request.AddSalariedEmployeeRequest;
import com.georgiev.payroll.request.Request;

public class AddSalariedEmployeeUseCase extends AddEmployeeUseCase {

  @Override
  public AbstractPayType getPayType(Request request) {

    AddSalariedEmployeeRequest req = (AddSalariedEmployeeRequest) request;
    return new Salaried(req.getSalary());
  }

  @Override
  public PaySchedule getSchedule() {
    return new MonthlySchedule();
  }
}
