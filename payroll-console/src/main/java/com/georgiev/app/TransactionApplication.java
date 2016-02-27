package com.georgiev.app;

import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.UseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;

public class TransactionApplication extends Application {

  private UseCaseFactory factory = new UseCaseFactoryImpl();
  private Request request;

  public TransactionApplication(Request request) {
    this.request = request;
  }

  @Override
  public void run() {
    UseCase makeAddCommisionedEmployee = factory.makeAddCommisionedEmployee();
    makeAddCommisionedEmployee.execute(request);
  }

}
