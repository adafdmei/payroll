package com.georgiev.test.usecases;

import java.util.Map;

import com.georgiev.builder.AddTimeCardRequestBuilder;
import com.georgiev.builder.RequestBuilderImpl;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddTimeCardUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;

public class AddTimeCardFactory {

  AddTimeCardRequestBuilder requestBuilder;
  AddTimeCardUseCaseFactory factory;

  public AddTimeCardFactory() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public void addTimeCard(Map<String, Object> data) {
    Request request = requestBuilder.buildAddTimeCardRequest(data);
    UseCase addTimeCardUseCase = factory.makeAddTimeCard();
    addTimeCardUseCase.execute(request);
  }

}
