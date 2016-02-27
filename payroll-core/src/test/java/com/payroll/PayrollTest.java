package com.payroll;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.georgiev.builder.ChangeEmployeeRequestBuilder;
import com.georgiev.builder.RequestBuilder;
import com.georgiev.builder.RequestBuilderImpl;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.payroll.impl.ServiceCharge;
import com.georgiev.payroll.impl.Member;
import com.georgiev.payroll.request.Request;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.AddSalesReceipt;
import com.georgiev.test.usecases.AddServiceCharge;
import com.georgiev.test.usecases.AddTimeCard;
import com.georgiev.test.usecases.PayEmployee;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.ChangeEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.UseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import com.georgiev.util.Constants;

public class PayrollTest {

  RequestBuilder requestBuilder;
  ChangeEmployeeRequestBuilder changeEmpRequestBuilder;
  UseCaseFactory factory;
  ChangeEmployeeUseCaseFactory changeEmpFactory;
  AddEmployee addEmp;
  AddSalesReceipt addSR;
  AddTimeCard addTc;
  Map<String, Object> data;
  Map<String, Object> newData;
  PayEmployee payEmp;
  Map<String, Object> payData;
  AddServiceCharge addSc;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    data = EmpData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    addSR = new AddSalesReceipt();
    addTc = new AddTimeCard();

    requestBuilder = new RequestBuilderImpl();
    changeEmpRequestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
    changeEmpFactory = new UseCaseFactoryImpl();
    payEmp = new PayEmployee();
    addSc = new AddServiceCharge();
  }

  private void addSalariedEmployee() {
    Request request = requestBuilder.buildSalariedEmployeeRequest(data);
    UseCase makeAddSalariedEmployee = factory.makeAddSalariedEmployee();
    makeAddSalariedEmployee.execute(request);
  }

  private void addCommissionedEmployee() {
    Request request = requestBuilder.buildCommissionedEmployeeRequest(data);
    UseCase makeAddCommissionedEmployee = factory.makeAddCommisionedEmployee();
    makeAddCommissionedEmployee.execute(request);
  }

  private void addHourlyEmployee() {
    Request request = requestBuilder.buildHourlyEmployeeRequest(data);
    UseCase makeAddHourlydEmployee = factory.makeAddHourlyEmployee();
    makeAddHourlydEmployee.execute(request);
  }

  private int getId() {
    return (int) data.get(Constants.EMPLOYEE_ID.name());
  }

  private int getMemberId() {
    return (int) data.get(Constants.MEMBER_ID.name());
  }

  private BigDecimal getHours() {
    return (BigDecimal) data.get(Constants.HOURS.name());
  }

  private void addTimeCard() {
    Request request = requestBuilder.buildAddTimeCardRequest(data);
    UseCase addTimeCardUseCase = factory.makeAddTimeCard();
    addTimeCardUseCase.execute(request);
  }

  @Test
  public void shouldAddServiceCharge() throws Exception {

    addCommissionedEmployee();

    final int memberId = 86;
    Map<String, Object> data2 = new HashMap<String, Object>();
    data2.put(Constants.MEMBER_ID.name(), memberId);
    data2.put(Constants.DATE.name(), date(11, 01, 2001));
    data2.put(Constants.CHARGE.name(), BigDecimal.valueOf(12.95));

    Employee e = GpayrollDatabase.getEmployee(getId());
    assertThat(e, is(notNullValue()));
    Request request = requestBuilder.buildAddSalesRecieptRequest(data);
    UseCase addSalesRecieptUseCase = factory.makeAddSalesReciept();
    addSalesRecieptUseCase.execute(request);

    Member af = new Member(getMemberId(), BigDecimal.valueOf(12.5));
    e.setUnionMembership(af);
    GpayrollDatabase.addUnionMember(getMemberId(), e);

    Request scRequest = requestBuilder.buildAddServiceChargeRequest(data2);
    UseCase addServiceChargeUseCase = factory.makeAddServiceCharge();
    addServiceChargeUseCase.execute(scRequest);

    ServiceCharge sc = af.getServiceCharge(date(11, 01, 2001));
    assertThat(sc, is(notNullValue()));
    assertThat(sc.getAmount(), is(BigDecimal.valueOf(12.95)));
  }

  @Test
  public void shouldPaySingleSalariedEmployee() throws Exception {
    addEmp.addSalariedEmployee(data);
    payEmp.paySingleEmployee(EmpData.getPayDayDataForSalariedEmployee());
    Employee employee = GpayrollDatabase.getEmployee(getId());

    Date payDate = EmpData.getPayDate(EmpData.getPayDayDataForSalariedEmployee());
    Paycheck pc = employee.getPaychecks().get(payDate);
    assertThat(pc, is(notNullValue()));
    assertThat(pc.getPayPeriodEndDate(), is(payDate));
    assertThat(pc.getGrossPay(), is(BigDecimal.valueOf(1000.00)));
    assertThat(pc.getField("Disposition"), is("Hold"));
    assertThat(pc.getDeductions(), is(BigDecimal.valueOf(0.0)));
    assertThat(pc.getNetPay(), is(BigDecimal.valueOf(1000.00)));
  }

  @Test
  public void shouldNotPaySingleSalariedEmployeeOnWrongDate() throws Exception {
    payData = EmpData.getPayDayWrongDataForSalariedEmployee();
    addEmp.addSalariedEmployee(data);
    payEmp.paySingleEmployee(payData);

    Date payDate = EmpData.getPayDate(payData);
    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);
    assertThat(pc, is(nullValue()));
  }

  @Test
  public void shouldNotPaySingleHourlyEmployeeNoTimeCards() throws Exception {
    payData = EmpData.getPayDayDataForHourlyEmployee();
    addEmp.addHourlyEmployee(data);
    payEmp.paySingleEmployee(payData);

    Date payDate = EmpData.getPayDate(payData);
    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);
    validatePaycheck(pc, getId(), payDate, new BigDecimal("0.00"));
  }

  @Test
  public void shouldPaySingleHourlyEmployeeOneTimeCard() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getTimeCardDataForEmployee());
    payData = EmpData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);

    Date payDate = EmpData.getPayDate(payData);
    //Date payDate = date(11, 9, 2001); // Friday

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);
    validatePaycheck(pc, getId(), payDate, new BigDecimal("30.48"));
  }

  @Test
  public void paySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getOvertimeTimeCardDataForEmployee());
    payData = EmpData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);

    Date payDate = EmpData.getPayDate(payData);
    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    final BigDecimal expectedPay = new BigDecimal("144.78"); // (8 + 1.5) * 15.24
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void paySingleHourlyEmployeeOnWrongDate() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getTimeCardDataForEmployee());
    payData = EmpData.getPayDayWrongDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpData.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    assertThat(pc, is(nullValue()));
  }

  @Test
  public void paySingleHourlyEmployeeTwoTimeCards() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getTimeCardDataForEmployee());
    addTc.addTimeCard(EmpData.getSecondTimeCardDataForEmployee());
    payData = EmpData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpData.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    final BigDecimal expectedPay = new BigDecimal("106.68"); // (2 + 5) * 15.24
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void paySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getTimeCardDataForEmployee());
    addTc.addTimeCard(EmpData.getPreviousPeriodTimeCardDataForEmployee());
    payData = EmpData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpData.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);
    final BigDecimal expectedPay = new BigDecimal("30.48"); // 2 * 15.24
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void paySingleCommissionedEmployeeNoSales() throws Exception {
    addEmp.addCommissionedEmployee(data);
    payData = EmpData.getPayDayDataForCommissionedEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpData.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    BigDecimal expectedPay = new BigDecimal("1153.85"); // 2500/mo * (12mo / 26checks)
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void paySingleCommissionedEmployeeOnWrongDate() throws Exception {
    addEmp.addCommissionedEmployee(data);
    payData = EmpData.getPayDayWrongDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpData.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    assertThat(pc, is(nullValue()));
  }

  @Test
  public void paySingleCommissionedEmployeeWithOneSale() throws Exception {
    addEmp.addCommissionedEmployee(data);
    addSR.addSalesReceipt(EmpData.getFirstSalesRecieptDataForEmployee());
    payData = EmpData.getPayDayDataForCommissionedEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpData.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    BigDecimal expectedPay = new BigDecimal("1169.85"); // 2500/mo * (12mo / 26checks) + 500.00 * 3.2%
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void paySingleCommissionedEmployeeWithTwoSales() throws Exception {
    addEmp.addCommissionedEmployee(data);
    addSR.addSalesReceipt(EmpData.getFirstSalesRecieptDataForEmployee());
    addSR.addSalesReceipt(EmpData.getSecondSalesRecieptDataForEmployee());
    payData = EmpData.getPayDayDataForCommissionedEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpData.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);
    BigDecimal expectedPay = new BigDecimal("1177.87"); // 2500/mo * (12mo / 26checks) + 750.50 * 3.2%
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void paySingleCommissionedEmployeeWithSaleInPreviousPayPeriod() throws Exception {
    addEmp.addCommissionedEmployee(data);
    addSR.addSalesReceipt(EmpData.getFirstSalesRecieptDataForEmployee());
    addSR.addSalesReceipt(EmpData.getPreviousPeriodSalesRecieptDataForEmployee());
    payData = EmpData.getPayDayDataForCommissionedEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpData.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    BigDecimal expectedPay = new BigDecimal("1169.85"); // 2500/mo * (12mo / 26checks) + 500.00 * 3.2%
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void salariedUnionMemberDues() throws Exception {
    addEmp.addSalariedEmployee(data);

    final int memberId = 86;
    Map<String, Object> data2 = new HashMap<String, Object>();
    data2.put(Constants.MEMBER_ID.name(), memberId);
    data2.put(Constants.WEEKLY_DUES.name(), BigDecimal.valueOf(9.42));
    data2.put(Constants.EMPLOYEE_ID.name(), 1);

    Request request = changeEmpRequestBuilder.buildChangeMemberRequest(data2);
    UseCase changeEmployeeMemberUseCase = changeEmpFactory.makeChangeEmployeeMember();
    changeEmployeeMemberUseCase.execute(request);

    payEmp.paySingleEmployee(EmpData.getPayDayDataForSalariedEmployee());
    Date payDate = EmpData.getPayDate(EmpData.getPayDayDataForSalariedEmployee());

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    validatePaycheckWithDeductions(pc,
                                   payDate,
                                   new BigDecimal("1000.00").setScale(2),
                                   new BigDecimal("952.90"),
                                   new BigDecimal("47.10")); // 1000 - (9.42 * 5)
  }

  @Test
  public void hourlyUnionMemberServiceCharge() throws Exception {

    final int memberId = 86;
    addEmp.addHourlyEmployee(data);
    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(data));
    Member af = new Member(memberId, BigDecimal.valueOf(9.42));
    e.setUnionMembership(af);
    GpayrollDatabase.addUnionMember(memberId, e);
    addTc.addTimeCard(EmpData.getThirdTimeCardDataForEmployee());
    addSc.addServiceCharge(EmpData.getServieChargeDataForHourlyEmployee());
    payEmp.paySingleEmployee(EmpData.getPayDayDataForHourlyEmployee());
    Date payDate = EmpData.getPayDate(EmpData.getPayDayDataForHourlyEmployee());

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    validatePaycheckWithDeductions(pc,
                                   payDate,
                                   new BigDecimal("121.92"),
                                   new BigDecimal("93.08"),
                                   new BigDecimal("28.84"));
  }

  @Test
  public void serviceChargesSpanningMultiplePayPeriods() throws Exception {
    final int memberId = 86;

    addEmp.addHourlyEmployee(data);
    Employee e = GpayrollDatabase.getEmployee(EmpData.getId(data));
    Member af = new Member(memberId, BigDecimal.valueOf(9.42));
    e.setUnionMembership(af);
    GpayrollDatabase.addUnionMember(memberId, e);

    Date earlyDate = date(11, 2, 2001); // previous Friday

    Date lateDate = date(11, 16, 2001); // next Friday
    addSc.addServiceCharge(EmpData.getServieChargeDataForHourlyEmployee());
//    ddServiceChargeRequest sct = new AddServiceChargeRequest(memberId, payDate, new BigDecimal("19.42"));
//    sct.execute();
//    AddServiceChargeRequest sctEarly = new AddServiceChargeRequest(memberId,
//                                                                   earlyDate,
//                                                                   new BigDecimal("100.00"));
//    sctEarly.execute();
//    AddServiceChargeRequest sctLate = new AddServiceChargeRequest(memberId,
//                                                                  lateDate,
//                                                                  new BigDecimal("200.00"));
//    sctLate.execute();
    addTc.addTimeCard(EmpData.getThirdTimeCardDataForEmployee());
    payEmp.paySingleEmployee(EmpData.getPayDayDataForHourlyEmployee());

    Date payDate = EmpData.getPayDate(EmpData.getPayDayDataForHourlyEmployee());

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    validatePaycheckWithDeductions(pc,
                                   payDate,
                                   new BigDecimal("121.92"),
                                   new BigDecimal("93.08"),
                                   new BigDecimal("28.84"));
  }

  // TODO: overtime pay for weekend time over 40 hours

  private void validatePaycheck(Paycheck pc, int empId, Date payDate, BigDecimal pay) {
    assertThat(pc, is(notNullValue()));
    assertThat(pc.getPayPeriodEndDate(), is(payDate));
    assertThat(pc.getGrossPay(), is(pay));
    assertThat(pc.getField("Disposition"), is("Hold"));
    assertThat(pc.getDeductions(), is(BigDecimal.valueOf(0.0)));
    assertThat(pc.getNetPay(), is(pay));
  }

  private void validatePaycheckWithDeductions(Paycheck pc,
                                              Date payDate,
                                              BigDecimal grossPay,
                                              BigDecimal netPay,
                                              BigDecimal deductions) {
    assertThat(pc, is(notNullValue()));
    assertThat(pc.getPayPeriodEndDate(), is(payDate));
    assertThat(pc.getGrossPay().setScale(2), is(grossPay.setScale(2)));
    assertThat(pc.getField("Disposition"), is("Hold"));
    assertThat(pc.getDeductions(), is(deductions));
    assertThat(pc.getNetPay(), is(netPay));
  }

  private Date date(int month, int day, int year) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.set(year, month - 1, day);
    return c.getTime();
  }

}
