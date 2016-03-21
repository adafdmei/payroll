package com.georgiev.test.payroll;

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
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.request.Request;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.utils.EmployeeData;
import com.georgiev.test.utils.EmployeeDataUtils;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.UseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;

public class DeleteEmployeePayrollTest {

  Map<String, Object> newData;
  AddEmployee addEmp;
  Map<String, Object> data;
  UseCaseFactory factory;
  AddEmployeeRequestBuilder addEmpRequestBuilder;
  DeleteEmployeeRequestBuilder delEmpRequestBuilder;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    delEmpRequestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
    data = EmployeeData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
  }

  @Test
  public void shouldDeleteEmployee() throws Exception {
    addEmp.addCommissionedEmployee(data);

    {
      Employee e = GpayrollDatabase.getEmployee(EmployeeDataUtils.getId(data));
      assertThat(e, is(notNullValue()));
    }
    deleteEmployee();
    {
      Employee e = GpayrollDatabase.getEmployee(EmployeeDataUtils.getId(data));
      assertThat(e, is(nullValue()));
    }
  }

  private void deleteEmployee() {
    Request request = delEmpRequestBuilder.buildDeleteEmployeeRequest(data);
    UseCase deleteEmployeeUseCase = factory.makeDeleteEmployee();
    deleteEmployeeUseCase.execute(request);
  }
}
