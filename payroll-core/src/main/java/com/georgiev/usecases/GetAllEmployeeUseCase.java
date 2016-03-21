package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import java.util.Map;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.Request;

public class GetAllEmployeeUseCase extends GetEmployeeUseCase {

  @Override
  protected Map<String, Employee> getEmployee(Request request) {
    return GpayrollDatabase.getAllEmployees();
  }

}
