package com.payroll;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.georgiev.builder.ChangeEmployeeRequestBuilder;
import com.georgiev.builder.RequestBuilder;
import com.georgiev.builder.RequestBuilderImpl;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.payroll.impl.Member;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.AddSalesReceipt;
import com.georgiev.test.usecases.AddServiceCharge;
import com.georgiev.test.usecases.AddTimeCard;
import com.georgiev.test.usecases.ChangeEmployeeToMember;
import com.georgiev.test.usecases.PayEmployee;
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
  ChangeEmployeeToMember changeEmp;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    data = EmpData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    addSR = new AddSalesReceipt();
    addTc = new AddTimeCard();
    changeEmp = new ChangeEmployeeToMember();

    requestBuilder = new RequestBuilderImpl();
    changeEmpRequestBuilder = new RequestBuilderImpl();
    factory = new UseCaseFactoryImpl();
    changeEmpFactory = new UseCaseFactoryImpl();
    payEmp = new PayEmployee();
    addSc = new AddServiceCharge();
  }

  private int getId() {
    return (int) data.get(Constants.EMPLOYEE_ID.name());
  }

  @Test
  public void shouldPaySingleSalariedEmployee() throws Exception {
    addEmp.addSalariedEmployee(data);
    payEmp.paySingleEmployee(EmpData.getPayDayDataForSalariedEmployee());
    Employee employee = GpayrollDatabase.getEmployee(getId());

    Date payDate = EmpDataUtils.getPayDate(EmpData.getPayDayDataForSalariedEmployee());
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

    Date payDate = EmpDataUtils.getPayDate(payData);
    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);
    assertThat(pc, is(nullValue()));
  }

  @Test
  public void shouldNotPaySingleHourlyEmployeeNoTimeCards() throws Exception {
    payData = EmpData.getPayDayDataForHourlyEmployee();
    addEmp.addHourlyEmployee(data);
    payEmp.paySingleEmployee(payData);

    Date payDate = EmpDataUtils.getPayDate(payData);
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
    Date payDate = EmpDataUtils.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);
    validatePaycheck(pc, getId(), payDate, new BigDecimal("30.48"));
  }

  @Test
  public void shouldPaySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getOvertimeTimeCardDataForEmployee());
    payData = EmpData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);

    Date payDate = EmpDataUtils.getPayDate(payData);
    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    final BigDecimal expectedPay = new BigDecimal("144.78"); // (8 + 1.5) * 15.24
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldPaySingleHourlyEmployeeOnWrongDate() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getTimeCardDataForEmployee());
    payData = EmpData.getPayDayWrongDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpDataUtils.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    assertThat(pc, is(nullValue()));
  }

  @Test
  public void shouldPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getTimeCardDataForEmployee());
    addTc.addTimeCard(EmpData.getSecondTimeCardDataForEmployee());
    payData = EmpData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpDataUtils.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    final BigDecimal expectedPay = new BigDecimal("106.68"); // (2 + 5) * 15.24
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldpaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmpData.getTimeCardDataForEmployee());
    addTc.addTimeCard(EmpData.getPreviousPeriodTimeCardDataForEmployee());
    payData = EmpData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmpDataUtils.getPayDate(payData);

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
    Date payDate = EmpDataUtils.getPayDate(payData);

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
    Date payDate = EmpDataUtils.getPayDate(payData);

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
    Date payDate = EmpDataUtils.getPayDate(payData);

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
    Date payDate = EmpDataUtils.getPayDate(payData);

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
    Date payDate = EmpDataUtils.getPayDate(payData);

    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);

    BigDecimal expectedPay = new BigDecimal("1169.85"); // 2500/mo * (12mo / 26checks) + 500.00 * 3.2%
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void salariedUnionMemberDues() throws Exception {
    addEmp.addSalariedEmployee(data);
    changeEmp.changeToMember(EmpData.getUnionMembershipDataForEmployee());
    payEmp.paySingleEmployee(EmpData.getPayDayDataForSalariedEmployee());
    Date payDate = EmpDataUtils.getPayDate(EmpData.getPayDayDataForSalariedEmployee());

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

    addEmp.addHourlyEmployee(data);
    changeEmp.changeToMember(EmpData.getUnionMembershipDataForEmployee());
    addTc.addTimeCard(EmpData.getThirdTimeCardDataForEmployee());
    addSc.addServiceCharge(EmpData.getServiceChargeDataForHourlyEmployee());
    payEmp.paySingleEmployee(EmpData.getPayDayDataForHourlyEmployee());
    Date payDate = EmpDataUtils.getPayDate(EmpData.getPayDayDataForHourlyEmployee());

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
    Employee e = GpayrollDatabase.getEmployee(EmpDataUtils.getId(data));
    Member af = new Member(memberId, BigDecimal.valueOf(9.42));
    e.setUnionMembership(af);
    GpayrollDatabase.addUnionMember(memberId, e);

    Date earlyDate = TestUtils.date(11, 2, 2001); // previous Friday

    Date lateDate = TestUtils.date(11, 16, 2001); // next Friday
    addSc.addServiceCharge(EmpData.getServiceChargeDataForHourlyEmployee());
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

    Date payDate = EmpDataUtils.getPayDate(EmpData.getPayDayDataForHourlyEmployee());

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

}
