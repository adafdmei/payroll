package com.georgiev.test.payroll;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.impl.ServiceCharge;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.AddSalesReceipt;
import com.georgiev.test.usecases.AddServiceCharge;
import com.georgiev.test.usecases.AddTimeCard;
import com.georgiev.test.usecases.ChangeEmployeeToMember;
import com.georgiev.test.utils.EmployeeData;
import com.georgiev.test.utils.EmployeeDataUtils;
import com.georgiev.test.utils.TestUtils;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class AddServiceChargePayrollTest {

  AddEmployee addEmp;
  AddSalesReceipt addSr;
  AddServiceCharge addSc;
  AddTimeCard addTc;
  ChangeEmployeeToMember chnageEmp;
  Map<String, Object> data;
  Map<String, Object> newData;
  PayrollDatabase db;

  @Before
  public void setup() {
    db = new InMemoryPayrollDatabase();
    data = EmployeeData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    addSr = new AddSalesReceipt();
    addSc = new AddServiceCharge();
    chnageEmp = new ChangeEmployeeToMember();

  }

  @Test
  public void shouldAddServiceCharge() throws Exception {
    addEmp.addCommissionedEmployee(db, data);
    Employee e = db.getEmployee(EmployeeDataUtils.getId(data));
    assertThat(e, is(notNullValue()));
    addSr.addSalesReceipt(db, data);
    newData = EmployeeData.getChangeUnionMembershipToMemberForEmployee();
    chnageEmp.changeToMember(db, newData);
    addSc.addServiceCharge(db, EmployeeData.getServieChargeDataForEmployee());

    Member member = (Member) e.getUnionMembership();
    ServiceCharge sc = member.getServiceCharge(TestUtils.date(11, 01, 2001));
    assertThat(sc, is(notNullValue()));
    assertThat(sc.getAmount(),
               is(EmployeeDataUtils.getServiceCharge(EmployeeData.getServieChargeDataForEmployee())));
  }
}
