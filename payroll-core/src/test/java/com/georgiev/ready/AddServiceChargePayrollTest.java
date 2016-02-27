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
import com.georgiev.payroll.impl.ServiceCharge;
import com.georgiev.payroll.impl.Member;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.AddSalesReceipt;
import com.georgiev.test.usecases.AddServiceCharge;
import com.georgiev.test.usecases.AddTimeCard;
import com.payroll.EmpData;
import com.payroll.TestUtils;

public class AddServiceChargePayrollTest {

  AddEmployee addEmp;
  AddSalesReceipt addSR;
  AddServiceCharge addSc;
  AddTimeCard addTc;
  Map<String, Object> data;
  Map<String, Object> newData;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    data = EmpData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    addSR = new AddSalesReceipt();
    addSc = new AddServiceCharge();
  }

  @Test
  public void shouldAddServiceCharge() throws Exception {
    addEmp.addCommissionedEmployee(data);
    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(data));
    assertThat(e, is(notNullValue()));
    addSR.addSalesReceipt(data);
    int memberId = EmpData.getMemberId(EmpData.getServieChargeDataForEmployee());
    Member af = new Member(memberId, BigDecimal.valueOf(12.5));
    e.setUnionMembership(af);
    GpayrollDatabase.addUnionMember(memberId, e);
    addSc.addServiceCharge(EmpData.getServieChargeDataForEmployee());

    ServiceCharge sc = af.getServiceCharge(TestUtils.date(11, 01, 2001));
    assertThat(sc, is(notNullValue()));
    assertThat(sc.getAmount(), is(BigDecimal.valueOf(12.95)));
  }
}
