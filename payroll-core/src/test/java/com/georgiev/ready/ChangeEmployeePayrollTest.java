package com.georgiev.ready;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.georgiev.builder.ChangeEmployeeRequestBuilder;
import com.georgiev.builder.RequestBuilderImpl;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.domain.PayType;
import com.georgiev.payroll.impl.BiweeklySchedule;
import com.georgiev.payroll.impl.Commissioned;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.MonthlySchedule;
import com.georgiev.payroll.impl.NoMember;
import com.georgiev.payroll.impl.Salaried;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.impl.WeeklySchedule;
import com.georgiev.payroll.request.Request;
import com.georgiev.test.usecases.AddEmployeeFactory;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.ChangeEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import com.payroll.EmpData;

public class ChangeEmployeePayrollTest {

  Map<String, Object> data;
  Map<String, Object> newData;
  AddEmployeeFactory addEmp;
  ChangeEmployeeRequestBuilder changeEmpRequestBuilder;
  ChangeEmployeeUseCaseFactory changeEmpFactory;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    data = EmpData.getStandardDataForEmployee();
    addEmp = new AddEmployeeFactory();

    changeEmpRequestBuilder = new RequestBuilderImpl();
    changeEmpFactory = new UseCaseFactoryImpl();
  }

  @Test
  public void shouldChangeEmployeeName() throws Exception {
    addEmp.addHourlyEmployee(data);
    changeEmployeeName();
    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(newData));
    assertThat(e, is(notNullValue()));
    assertThat(e.getName(), is(EmpData.getName(newData)));
  }

  private void changeEmployeeName() {
    newData = EmpData.getChangeNameDataForEmployee();
    Request request = changeEmpRequestBuilder.buildChangeEmployeeNameRequest(newData);
    UseCase changeEmployeeNameUseCase = changeEmpFactory.makeChangeEmployeeName();
    changeEmployeeNameUseCase.execute(request);
  }

  @Test
  public void shouldChangeEmployeeToHourly() throws Exception {
    addEmp.addCommissionedEmployee(data);
    changeEmployeeToHourly();

    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(newData));
    assertThat(e, is(notNullValue()));
    PayType pc = e.getPayType();
    assertThat(pc, instanceOf(Hourly.class));
    Hourly hc = (Hourly) pc;
    assertThat(hc.getHourlyRate(), is(EmpData.getHourlyRate(newData)));
    PaySchedule ps = e.getPaySchedule();
    assertThat(ps, instanceOf(WeeklySchedule.class));
  }

  private void changeEmployeeToHourly() {
    newData = EmpData.getChangeTypeHourlyDataForEmployee();
    Request request = changeEmpRequestBuilder.buildChangeEmployeeHourlyRequest(newData);
    UseCase changeEmployeeToHourlyUseCase = changeEmpFactory.makeChangeEmployeeHourly();
    changeEmployeeToHourlyUseCase.execute(request);
  }

  @Test
  public void shouldChangeEmployeeToSalaried() throws Exception {
    addEmp.addCommissionedEmployee(data);
    changeEmployeeToSalaried();

    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(newData));
    assertThat(e, is(notNullValue()));
    PayType pc = e.getPayType();
    assertThat(pc, instanceOf(Salaried.class));
    Salaried sc = (Salaried) pc;
    assertThat(sc.getSalary(), is(BigDecimal.valueOf(1000.00)));
    PaySchedule ps = e.getPaySchedule();
    assertThat(ps, instanceOf(MonthlySchedule.class));
  }

  private void changeEmployeeToSalaried() {
    newData = EmpData.getChangeTypeSalariedDataForEmployee();
    Request request = changeEmpRequestBuilder.buildChangeEmployeeSalariedRequest(newData);
    UseCase changeEmployeeToSalariedUseCase = changeEmpFactory.makeChangeEmployeeSalaried();
    changeEmployeeToSalariedUseCase.execute(request);
  }

  @Test
  public void shouldChangeEmployeeToCommissioned() throws Exception {
    addEmp.addSalariedEmployee(data);
    changeEmployeeToCommissioned();

    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(newData));
    assertThat(e, is(notNullValue()));
    PayType pc = e.getPayType();
    assertThat(pc, instanceOf(Commissioned.class));
    Commissioned cc = (Commissioned) pc;
    assertThat(cc.getBasePay(), is(new BigDecimal("2500.00")));
    assertThat(cc.getCommissionRate(), is(BigDecimal.valueOf(3.2)));
    PaySchedule ps = e.getPaySchedule();
    assertThat(ps, instanceOf(BiweeklySchedule.class));
  }

  private void changeEmployeeToCommissioned() {
    newData = EmpData.getChangeTypeComissionedDataForEmployee();
    Request request = changeEmpRequestBuilder.buildChangeEmployeeCommissionedRequest(newData);
    UseCase changeEmployeeToCommissionedUseCase = changeEmpFactory.makeChangeEmployeeCommissioned();
    changeEmployeeToCommissionedUseCase.execute(request);
  }

  @Test
  public void shouldChangeToMember() throws Exception {
    addEmp.addHourlyEmployee(data);
    changeToMember();

    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(newData));
    assertThat(e, is(notNullValue()));
    UnionMembership af = e.getAffiliation();
    assertThat(af, instanceOf(Member.class));
    Member uf = (Member) af;
    int memberId = EmpData.getMemberId(newData);
    assertThat(uf.getMemberId(), is(memberId));
    assertThat(uf.getWeeklyDues(), is(BigDecimal.valueOf(99.42)));
    Employee member = GpayrollDatabase.getUnionMember(memberId);
    assertThat(member, is(notNullValue()));
    assertThat(e.equals(member), is(true));
  }

  private void changeToMember() {
    newData = EmpData.getChangeAffiliationToMemberForEmployee();
    Request request = changeEmpRequestBuilder.buildChangeMemberRequest(newData);
    UseCase changeEmployeeMemberUseCase = changeEmpFactory.makeChangeEmployeeMember();
    changeEmployeeMemberUseCase.execute(request);
  }

  @Test
  public void shouldChangeToNoMember() throws Exception {
    addEmp.addHourlyEmployee(data);
    final int memberId = EmpData.getMemberId(data);

    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(data));
    assertThat(e, is(notNullValue()));
    Member uf = new Member(memberId, BigDecimal.valueOf(12.5));
    e.setUnionMembership(uf);
    GpayrollDatabase.addUnionMember(memberId, e);
    changeToNoMember();

    UnionMembership af = e.getAffiliation();
    assertThat(af, instanceOf(NoMember.class));
    Employee member = GpayrollDatabase.getUnionMember(memberId);
    assertThat(member, is(nullValue()));
  }

  private void changeToNoMember() {
    newData = EmpData.getEmployeeIdData();
    Request request = changeEmpRequestBuilder.buildChangeNoMemberRequest(data);
    UseCase changeEmployeeMemberUseCase = changeEmpFactory.makeChangeEmployeeNoMember();
    changeEmployeeMemberUseCase.execute(request);
  }

  @Test
  public void changeToNoMember_alreadyUnaffiliatedEmployee() throws Exception {
    addEmp.addCommissionedEmployee(data);
    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(data));
    assertThat(e, is(notNullValue()));

    changeToNoMember();
    UnionMembership af = e.getAffiliation();
    assertThat(af, instanceOf(NoMember.class));
  }
}