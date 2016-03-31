package com.georgiev.test.usecases;

import com.georgiev.builder.AddEmployeeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;

public class AddEmployee {

  AddEmployeeRequestBuilder requestBuilder;
  AddEmployeeUseCaseFactory factory;

  public AddEmployee() {
    requestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
  }

  public void addSalariedEmployee(PayrollDatabase db, Map<String, Object> data) {

    Request request = requestBuilder.buildSalariedEmployeeRequest(data);
    UseCase makeAddSalariedEmployee = factory.makeAddSalariedEmployee(db);
    makeAddSalariedEmployee.execute(request);
  }

  public void addCommissionedEmployee(PayrollDatabase db, Map<String, Object> data) {
    Request request = requestBuilder.buildCommissionedEmployeeRequest(data);

    UseCase makeAddCommissionedEmployee = factory.makeAddCommissionedEmployee(db);
    makeAddCommissionedEmployee.execute(request);
  }

  public void addHourlyEmployee(PayrollDatabase db, Map<String, Object> data) {
    Request request = requestBuilder.buildHourlyEmployeeRequest(data);
    UseCase makeAddHourlydEmployee = factory.makeAddHourlyEmployee(db);
    makeAddHourlydEmployee.execute(request);
  }
}
