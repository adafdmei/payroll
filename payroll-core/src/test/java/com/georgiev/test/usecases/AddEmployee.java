package com.georgiev.test.usecases;

import java.util.Map;

import com.georgiev.builder.AddEmployeeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;

public class AddEmployee {

  AddEmployeeRequestBuilder requestBuilder;
  AddEmployeeUseCaseFactory factory;

  public AddEmployee() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public void addSalariedEmployee(Map<String, Object> data) {
    Request request = requestBuilder.buildSalariedEmployeeRequest(data);
    UseCase makeAddSalariedEmployee = factory.makeAddSalariedEmployee();
    makeAddSalariedEmployee.execute(request);
  }

  public void addCommissionedEmployee(Map<String, Object> data) {
    Request request = requestBuilder.buildCommissionedEmployeeRequest(data);
    UseCase makeAddCommissionedEmployee = factory.makeAddCommissionedEmployee();
    makeAddCommissionedEmployee.execute(request);
  }

  public void addHourlyEmployee(Map<String, Object> data) {
    Request request = requestBuilder.buildHourlyEmployeeRequest(data);
    UseCase makeAddHourlydEmployee = factory.makeAddHourlyEmployee();
    makeAddHourlydEmployee.execute(request);
  }

}
