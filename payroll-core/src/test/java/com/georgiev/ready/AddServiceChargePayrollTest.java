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
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.impl.ServiceCharge;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.AddSalesReceipt;
import com.georgiev.test.usecases.AddServiceCharge;
import com.georgiev.test.usecases.AddTimeCard;
import com.georgiev.test.usecases.ChangeEmployeeToMember;
import com.payroll.EmpData;
import com.payroll.EmpDataUtils;
import com.payroll.TestUtils;

public class AddServiceChargePayrollTest {

  AddEmployee addEmp;
  AddSalesReceipt addSr;
  AddServiceCharge addSc;
  AddTimeCard addTc;
  ChangeEmployeeToMember chnageEmp;
  Map<String, Object> data;
  Map<String, Object> newData;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    data = EmpData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    addSr = new AddSalesReceipt();
    addSc = new AddServiceCharge();
    chnageEmp = new ChangeEmployeeToMember();

  }

  @Test
  public void shouldAddServiceCharge() throws Exception {
    addEmp.addCommissionedEmployee(data);
    Employee e = GpayrollDatabase.getEmployee(EmpDataUtils.getId(data));
    assertThat(e, is(notNullValue()));
    addSr.addSalesReceipt(data);
    newData = EmpData.getChangeAffiliationToMemberForEmployee();
    chnageEmp.changeToMember(newData);
    addSc.addServiceCharge(EmpData.getServieChargeDataForEmployee());

    Member member = (Member) e.getUnionMembership();
    ServiceCharge sc = member.getServiceCharge(TestUtils.date(11, 01, 2001));
    assertThat(sc, is(notNullValue()));
    assertThat(sc.getAmount(), is(BigDecimal.valueOf(12.95)));
  }
}
