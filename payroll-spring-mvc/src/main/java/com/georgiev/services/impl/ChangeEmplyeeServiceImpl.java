package com.georgiev.services.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.georgiev.builder.ChangeEmployeeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.request.Request;
import com.georgiev.services.ChangeEmplyeeService;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.ChangeEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;

@Service("changeEmplyeeService")
public class ChangeEmplyeeServiceImpl implements ChangeEmplyeeService {

  ChangeEmployeeRequestBuilder requestBuilder;
  ChangeEmployeeUseCaseFactory useCaseFactory;

  @PostConstruct
  private void init() {
    requestBuilder = new RequestBuilderImpl();
    useCaseFactory = new UseCaseFactoryImpl();
  }

  private String response(UseCase useCase) {
    return "";//useCase.getResponse().getAsString();
  }

  private void execute(Request request, UseCase useCase) {
    useCase.execute(request);
  }

  @Override
  public String changeEmplyeeName(Map<String, Object> data) {
    Request request = requestBuilder.buildChangeEmployeeNameRequest(data);
    UseCase useCase = useCaseFactory.makeChangeEmployeeName();
    execute(request, useCase);
    return response(useCase);
  }

  @Override
  public String changeEmplyeeToCommissioned(Map<String, Object> data) {
    Request request = requestBuilder.buildChangeEmployeeCommissionedRequest(data);
    UseCase useCase = useCaseFactory.makeChangeEmployeeCommissioned();
    execute(request, useCase);
    return response(useCase);
  }

  @Override
  public String changeEmplyeeToHourly(Map<String, Object> data) {
    Request request = requestBuilder.buildChangeEmployeeHourlyRequest(data);
    UseCase useCase = useCaseFactory.makeChangeEmployeeHourly();
    execute(request, useCase);
    return response(useCase);
  }

  @Override
  public String changeEmplyeeToSalaried(Map<String, Object> data) {
    Request request = requestBuilder.buildChangeEmployeeSalariedRequest(data);
    UseCase useCase = useCaseFactory.makeChangeEmployeeSalaried();
    execute(request, useCase);
    return response(useCase);
  }
}
