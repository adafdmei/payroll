package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.impl.MonthlySchedule;
import com.georgiev.payroll.impl.Salaried;
import com.georgiev.payroll.request.ChangeSalariedRequest;
import com.georgiev.payroll.request.Request;

public class ChangeSalariedUseCase extends ChangePayTypeUseCase {

  public ChangeSalariedUseCase(PayrollDatabase payrollDatabase) {
    super(payrollDatabase);
  }

  @Override
  protected AbstractPayType getClassification(Request request) {
    ChangeSalariedRequest cReq = (ChangeSalariedRequest) request;
    return new Salaried(cReq.getSalary());
  }

  @Override
  protected PaySchedule getSchedule() {
    return new MonthlySchedule();
  }
}
