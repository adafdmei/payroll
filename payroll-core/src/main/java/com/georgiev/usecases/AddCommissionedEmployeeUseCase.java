package com.georgiev.usecases;

import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.impl.BiweeklySchedule;
import com.georgiev.payroll.impl.Commissioned;
import com.georgiev.payroll.request.AddCommisionedEmployeeRequest;
import com.georgiev.payroll.request.Request;

public class AddCommissionedEmployeeUseCase extends AddEmployeeUseCase {

  @Override
  public AbstractPayType getClassification(Request request) {
    AddCommisionedEmployeeRequest req = (AddCommisionedEmployeeRequest) request;
    return new Commissioned(req.getBasePay(), req.getCommissionRate());
  }

  @Override
  public PaySchedule getSchedule() {
    return new BiweeklySchedule();
  }
}
