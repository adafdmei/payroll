package com.georgiev.test.payroll;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;
import static org.junit.Assert.assertEquals;

import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.FindEmployee;
import com.georgiev.test.utils.EmployeeData;
import com.georgiev.test.utils.EmployeeDataUtils;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class FindEmployeePayrollTest {

  AddEmployee addEmp;
  FindEmployee findEmp;
  Map<String, Object> data;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    data = EmployeeData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    findEmp = new FindEmployee();
  }

  @Test
  public void shouldFindEmployee() throws Exception {
    addEmp.addSalariedEmployee(data);
    Map<String, Object> empMap = findEmp.findEmployee(data);

    Employee e = (Employee) empMap.get(String.valueOf(EmployeeDataUtils.getId(data)));
    assertEquals("Bob", e.getName());
  }

  @Test
  public void shouldFindAllEmployees() throws Exception {
    addEmp.addSalariedEmployee(data);
    Map<String, Object> empMap = findEmp.findAllEmployees(data);

    assertEquals(empMap.size(), 1);
  }

}
