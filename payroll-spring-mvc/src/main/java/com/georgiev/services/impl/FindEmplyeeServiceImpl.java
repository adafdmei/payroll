package com.georgiev.services.impl;

import com.georgiev.builder.FindEmployeeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.services.FindEmplyeeService;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.FindEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service("findEmplyeeService")
public class FindEmplyeeServiceImpl implements FindEmplyeeService {

  FindEmployeeRequestBuilder requestBuilder;
  FindEmployeeUseCaseFactory useCaseFactory;

  @PostConstruct
  private void init() {
    requestBuilder = new RequestBuilderImpl();
    useCaseFactory = new UseCaseFactoryImpl();
  }

  private Map<String, Object> response(UseCase useCase) {
    return useCase.getResponse().getAsMap();
  }

  private void execute(Request request, UseCase useCase) {
    useCase.execute(request);
  }

  @Override
  public Map<String, Object> findAllEmployees(PayrollDatabase payrollDatabase) {
    Request request = requestBuilder.buildFindAllEmployeesRequest();
    UseCase useCase = useCaseFactory.makeFindAllEmployees(payrollDatabase);
    execute(request, useCase);

    return response(useCase);
  }

  @Override
  public Map<String, Object> findEmployee(Map<String, Object> data, PayrollDatabase payrollDatabase) {
    Request request = requestBuilder.buildFindEmployeeRequest(data);
    UseCase useCase = useCaseFactory.makeFindEmployee(payrollDatabase);
    execute(request, useCase);
    return response(useCase);
  }

}
