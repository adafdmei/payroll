package com.georgiev.test.usecases;

import java.util.Map;

import com.georgiev.builder.PayEmployeeRequestBuilder;
import com.georgiev.builder.RequestBuilderImpl;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.PayEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;

public class PayEmployeesTestUtils {

  PayEmployeeRequestBuilder requestBuilder;
  PayEmployeeUseCaseFactory factory;

  public PayEmployeesTestUtils() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public void paySingleEmployee(Map<String, Object> data) throws Exception {
    Request request = requestBuilder.buildPayDayRequest(data);
    UseCase payDayUseCase = factory.makePayDay();
    payDayUseCase.execute(request);
  }

}
