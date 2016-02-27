package com.georgiev.ready;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.Commissioned;
import com.georgiev.payroll.impl.SalesReceipt;
import com.georgiev.test.usecases.AddEmployeeFactory;
import com.georgiev.test.usecases.AddSalesReceipTestUtils;
import com.payroll.EmpData;

public class AddSalesReceiptPayrollTest {

  Map<String, Object> data;
  AddSalesReceipTestUtils addSR;
  AddEmployeeFactory addEmp;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    data = EmpData.getStandardDataForEmployee();
    addEmp = new AddEmployeeFactory();
    addSR = new AddSalesReceipTestUtils();
  }

  @Test
  public void shouldAddSalesReceipt() throws Exception {
    addEmp.addCommissionedEmployee(data);
    addSR.addSalesReceipt(data);
    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(data));

    assertThat(e, is(notNullValue()));
    Commissioned cc = (Commissioned) e.getPayType();
    SalesReceipt sr = cc.getSalesReceipt(EmpData.getDate(data));
    assertThat(sr, is(notNullValue()));
    assertThat(sr.getAmount(), is(EmpData.getSoldAmount(data)));
  }

}
