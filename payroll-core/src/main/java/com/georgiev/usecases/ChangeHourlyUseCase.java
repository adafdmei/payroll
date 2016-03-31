package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.WeeklySchedule;
import com.georgiev.payroll.request.ChangeHourlyRequest;
import com.georgiev.payroll.request.Request;

public class ChangeHourlyUseCase extends ChangePayTypeUseCase {

  public ChangeHourlyUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  protected AbstractPayType getClassification(Request request) {
    ChangeHourlyRequest cReq = (ChangeHourlyRequest) request;
    return new Hourly(cReq.getHourlyRate());
  }

  @Override
  protected PaySchedule getSchedule() {
    return new WeeklySchedule();
  }

  @Override
  public Response getResponse() {
    // TODO Auto-generated method stub
    return null;
  }

}
