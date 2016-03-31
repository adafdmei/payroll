package com.georgiev.test.payroll;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.TimeCard;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.AddTimeCard;
import com.georgiev.test.utils.EmployeeData;
import com.georgiev.test.utils.EmployeeDataUtils;
import com.georgiev.util.Constants;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class AddTimeCardPayrollTest {

  Map<String, Object> data;
  AddEmployee addEmp;
  AddTimeCard addTc;
  PayrollDatabase db;

  @Before
  public void setup() {
    db = new InMemoryPayrollDatabase();

    data = EmployeeData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    addTc = new AddTimeCard();
  }

  private int getId() {
    return (int) data.get(Constants.ID.name());
  }

  private BigDecimal getHours() {
    return (BigDecimal) data.get(Constants.HOURS.name());
  }

  @Test
  public void shouldAddTimeCard() throws Exception {
    addEmp.addHourlyEmployee(db, data);
    addTc.addTimeCard(db, data);
    Employee e = db.getEmployee(getId());

    assertThat(e, is(notNullValue()));
    Hourly hc = (Hourly) e.getPayType();
    TimeCard tc = hc.getTimeCard(EmployeeDataUtils.getDate(data));
    assertThat(tc, is(notNullValue()));
    assertThat(tc.getHours(), is(getHours()));
  }
}
