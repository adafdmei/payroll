package com.georgiev.test.usecases;

import com.georgiev.builder.FindEmployeeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.FindEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;

public class FindEmployee {

  FindEmployeeRequestBuilder requestBuilder;
  FindEmployeeUseCaseFactory factory;

  public FindEmployee() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public Map<String, Object> findEmployee(PayrollDatabase db, Map<String, Object> data) {
    Request request = requestBuilder.buildFindEmployeeRequest(data);
    UseCase usecase = factory.makeFindEmployee(db);
    usecase.execute(request);
    return usecase.getResponse().getAsMap();
  }

  public Map<String, Object> findAllEmployees(PayrollDatabase db, Map<String, Object> data) {
    Request request = requestBuilder.buildFindAllEmployeesRequest();
    UseCase usecase = factory.makeFindAllEmployees(db);
    usecase.execute(request);
    return usecase.getResponse().getAsMap();
  }
}
