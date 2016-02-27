package com.georgiev.ready;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.georgiev.builder.AddEmployeeRequestBuilder;
import com.georgiev.builder.DeleteEmployeeRequestBuilder;
import com.georgiev.builder.RequestBuilderImpl;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.Request;
import com.georgiev.test.usecases.AddEmployeeFactory;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.UseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import com.payroll.EmpData;

public class DeleteEmployeePayrollTest {

  Map<String, Object> newData;
  AddEmployeeFactory addEmp;
  Map<String, Object> data;
  UseCaseFactory factory;
  AddEmployeeRequestBuilder addEmpRequestBuilder;
  DeleteEmployeeRequestBuilder delEmpRequestBuilder;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    delEmpRequestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
    data = EmpData.getStandardDataForEmployee();
    addEmp = new AddEmployeeFactory();
  }

  @Test
  public void shouldDeleteEmployee() throws Exception {
    addEmp.addCommissionedEmployee(data);

    {
      Employee e = GpayrollDatabase.getEmployee(EmpData.getId(data));
      assertThat(e, is(notNullValue()));
    }
    deleteEmployee();
    {
      Employee e = GpayrollDatabase.getEmployee(EmpData.getId(data));
      assertThat(e, is(nullValue()));
    }
  }

  private void deleteEmployee() {
    Request request = delEmpRequestBuilder.buildDeleteEmployeeRequest(data);
    UseCase deleteEmployeeUseCase = factory.makeDeleteEmployee();
    deleteEmployeeUseCase.execute(request);
  }
}
