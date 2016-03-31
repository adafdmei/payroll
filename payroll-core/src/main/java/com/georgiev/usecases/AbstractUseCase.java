package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;

public abstract class AbstractUseCase implements UseCase {

  protected final PayrollDatabase payrollDatabase;
  protected Response response;

  public AbstractUseCase(PayrollDatabase payrollDatabase) {
    this.payrollDatabase = payrollDatabase;
  }

  @Override
  public Response getResponse() {
    return response;
  }
}
