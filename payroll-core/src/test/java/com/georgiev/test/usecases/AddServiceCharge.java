package com.georgiev.test.usecases;

import java.util.Map;

import com.georgiev.builder.AddServiceChargeRequestBuilder;
import com.georgiev.builder.RequestBuilderImpl;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddServiceChargeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;

public class AddServiceCharge {

  AddServiceChargeRequestBuilder requestBuilder;
  AddServiceChargeUseCaseFactory factory;

  public AddServiceCharge() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public void addServiceCharge(Map<String, Object> data) throws Exception {
    Request scRequest = requestBuilder.buildAddServiceChargeRequest(data);
    UseCase addServiceChargeUseCase = factory.makeAddServiceCharge();
    addServiceChargeUseCase.execute(scRequest);
  }
}
