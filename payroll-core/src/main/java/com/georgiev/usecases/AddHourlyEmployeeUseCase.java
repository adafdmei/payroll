package com.georgiev.usecases;

import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.WeeklySchedule;
import com.georgiev.payroll.request.AddHourlyEmployeeRequest;
import com.georgiev.payroll.request.Request;

public class AddHourlyEmployeeUseCase extends AddEmployeeUseCase {

  @Override
  public AbstractPayType getClassification(Request request) {
    AddHourlyEmployeeRequest req = (AddHourlyEmployeeRequest) request;
    return new Hourly(req.getHourlyRate());
  }

  @Override
  public PaySchedule getSchedule() {
    return new WeeklySchedule();
  }
}