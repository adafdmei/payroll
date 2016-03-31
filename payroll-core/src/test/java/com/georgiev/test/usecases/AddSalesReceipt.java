package com.georgiev.test.usecases;

import com.georgiev.builder.AddSalesRecieptRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddSalesRecieptUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;

public class AddSalesReceipt {

  AddSalesRecieptRequestBuilder requestBuilder;
  AddSalesRecieptUseCaseFactory factory;

  public AddSalesReceipt() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public void addSalesReceipt(PayrollDatabase db, Map<String, Object> data) {
    Request request = requestBuilder.buildAddSalesRecieptRequest(data);
    UseCase addSalesRecieptUseCase = factory.makeAddSalesReciept(db);
    addSalesRecieptUseCase.execute(request);
  }
}
