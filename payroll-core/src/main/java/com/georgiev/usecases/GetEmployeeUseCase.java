package com.georgiev.usecases;

import java.util.Map;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.Request;

public abstract class GetEmployeeUseCase implements UseCase {

  private Response response;

  @Override
  public void execute(Request request) {
    Map<String, Employee> employees = getEmployee(request);
    response = new EmployeeResponse(employees);
  }

  protected abstract Map<String, Employee> getEmployee(Request request);

  @Override
  public Response getResponse() {
    return response;
  }
}
