package com.georgiev.services.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.georgiev.builder.FindEmployeeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.request.Request;
import com.georgiev.services.FindEmplyeeService;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.FindEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;

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
  public Map<String, Object> findAllEmployees() {
    Request request = requestBuilder.buildGetAllEmployeeRequest();
    UseCase useCase = useCaseFactory.makeGetAllEmployees();
    execute(request, useCase);

    return response(useCase);
  }

  @Override
  public Map<String, Object> findEmployee(Map<String, Object> data) {
    Request request = requestBuilder.buildFindAnEmployeeRequest(data);
    UseCase useCase = useCaseFactory.makeFindAnEmployee();
    execute(request, useCase);
    return response(useCase);
  }

}
