package com.georgiev.test.usecases;

import com.georgiev.builder.AddServiceChargeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddServiceChargeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;

public class AddServiceCharge {

  AddServiceChargeRequestBuilder requestBuilder;
  AddServiceChargeUseCaseFactory factory;

  public AddServiceCharge() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public void addServiceCharge(PayrollDatabase db, Map<String, Object> data) throws Exception {
    Request scRequest = requestBuilder.buildAddServiceChargeRequest(data);
    UseCase addServiceChargeUseCase = factory.makeAddServiceCharge(db);
    addServiceChargeUseCase.execute(scRequest);
  }
}
