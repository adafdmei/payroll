package com.georgiev.test.usecases;

import com.georgiev.builder.AddTimeCardRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddTimeCardUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;

public class AddTimeCard {

  AddTimeCardRequestBuilder requestBuilder;
  AddTimeCardUseCaseFactory factory;

  public AddTimeCard() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public void addTimeCard(PayrollDatabase db, Map<String, Object> data) {
    Request request = requestBuilder.buildAddTimeCardRequest(data);
    UseCase addTimeCardUseCase = factory.makeAddTimeCard(db);
    addTimeCardUseCase.execute(request);
  }

}
