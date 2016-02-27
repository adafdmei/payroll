package com.georgiev.ready;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.TimeCard;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.AddTimeCard;
import com.georgiev.util.Constants;
import com.payroll.EmpData;
import com.payroll.EmpDataUtils;

public class AddTimeCardPayrollTest {

  Map<String, Object> data;
  AddEmployee addEmp;
  AddTimeCard addTc;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();

    data = EmpData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    addTc = new AddTimeCard();
  }

  private int getId() {
    return (int) data.get(Constants.EMPLOYEE_ID.name());
  }

  private BigDecimal getHours() {
    return (BigDecimal) data.get(Constants.HOURS.name());
  }

  @Test
  public void shouldAddTimeCard() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(data);
    Employee e = GpayrollDatabase.getEmployee(getId());

    assertThat(e, is(notNullValue()));
    Hourly hc = (Hourly) e.getPayType();
    TimeCard tc = hc.getTimeCard(EmpDataUtils.getDate(data));
    assertThat(tc, is(notNullValue()));
    assertThat(tc.getHours(), is(getHours()));
  }
}
