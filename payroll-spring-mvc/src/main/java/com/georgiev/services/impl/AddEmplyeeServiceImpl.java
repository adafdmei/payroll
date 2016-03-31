package com.georgiev.services.impl;

import com.georgiev.builder.AddEmployeeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.services.AddEmplyeeService;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service("addEmplyeeService")
public class AddEmplyeeServiceImpl implements AddEmplyeeService {

  AddEmployeeRequestBuilder requestBuilder;
  AddEmployeeUseCaseFactory useCaseFactory;

  @PostConstruct
  private void init() {
    requestBuilder = new RequestBuilderImpl();
    useCaseFactory = new UseCaseFactoryImpl();
  }

  @Override
  public String addSalariedEmpolyee(Map<String, Object> data, PayrollDatabase payrollDatabase) {
    Request request = requestBuilder.buildSalariedEmployeeRequest(data);
    UseCase useCase = useCaseFactory.makeAddSalariedEmployee(payrollDatabase);
    execute(request, useCase);
    return response(useCase);
  }

  private String response(UseCase useCase) {
    return useCase.getResponse().getAsString();
  }

  private void execute(Request request, UseCase useCase) {
    useCase.execute(request);
  }

  @Override
  public String addCommisionedEmployee(Map<String, Object> data, PayrollDatabase payrollDatabase) {
    Request request = requestBuilder.buildCommissionedEmployeeRequest(data);
    UseCase useCase = useCaseFactory.makeAddCommissionedEmployee(payrollDatabase);
    execute(request, useCase);
    return response(useCase);
  }

  @Override
  public String addHourlyEmployee(Map<String, Object> data, PayrollDatabase payrollDatabase) {
    Request request = requestBuilder.buildHourlyEmployeeRequest(data);
    UseCase useCase = useCaseFactory.makeAddHourlyEmployee(payrollDatabase);
    execute(request, useCase);
    return response(useCase);
  }

}
