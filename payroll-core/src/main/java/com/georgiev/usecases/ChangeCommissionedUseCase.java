package com.georgiev.usecases;

import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.impl.BiweeklySchedule;
import com.georgiev.payroll.impl.Commissioned;
import com.georgiev.payroll.request.ChangeCommissionedRequest;
import com.georgiev.payroll.request.Request;

public class ChangeCommissionedUseCase extends ChangePayTypeUseCase {

  @Override
  protected AbstractPayType getClassification(Request request) {
    ChangeCommissionedRequest cReq = (ChangeCommissionedRequest) request;
    return new Commissioned(cReq.getSalary(), cReq.getCommissionRate());
  }

  @Override
  protected PaySchedule getSchedule() {
    return new BiweeklySchedule();
  }
}
