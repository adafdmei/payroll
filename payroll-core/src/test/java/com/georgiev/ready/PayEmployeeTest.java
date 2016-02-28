package com.georgiev.ready;

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

import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.Paycheck;
import com.georgiev.test.usecases.AddEmployee;
import com.georgiev.test.usecases.AddSalesReceipt;
import com.georgiev.test.usecases.AddServiceCharge;
import com.georgiev.test.usecases.AddTimeCard;
import com.georgiev.test.usecases.ChangeEmployeeToMember;
import com.georgiev.test.usecases.PayEmployee;
import com.georgiev.util.Constants;
import com.payroll.EmployeeData;
import com.payroll.EmployeeDataUtils;

public class PayEmployeeTest {

  AddEmployee addEmp;
  AddSalesReceipt addSR;
  AddTimeCard addTc;
  PayEmployee payEmp;
  Map<String, Object> payData;
  AddServiceCharge addSc;
  ChangeEmployeeToMember changeEmp;
  Map<String, Object> data;
  Map<String, Object> newData;

  @Before
  public void setup() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
    data = EmployeeData.getStandardDataForEmployee();
    addEmp = new AddEmployee();
    addSR = new AddSalesReceipt();
    addTc = new AddTimeCard();
    changeEmp = new ChangeEmployeeToMember();
    payEmp = new PayEmployee();
    addSc = new AddServiceCharge();
  }

  @Test
  public void shouldPaySingleSalariedEmployee() throws Exception {
    addEmp.addSalariedEmployee(data);
    payEmp.paySingleEmployee(EmployeeData.getPayDayDataForSalariedEmployee());
    Date payDate = EmployeeDataUtils.getPayDate(EmployeeData.getPayDayDataForSalariedEmployee());
    Paycheck pc = getPayCheckForEmployee(payDate);

    assertThat(pc, is(notNullValue()));
    assertThat(pc.getPayPeriodEndDate(), is(payDate));
    assertThat(pc.getGrossPay(), is(BigDecimal.valueOf(1000.00)));
    assertThat(pc.getField("Disposition"), is("Hold"));
    assertThat(pc.getDeductions(), is(BigDecimal.valueOf(0.0)));
    assertThat(pc.getNetPay(), is(BigDecimal.valueOf(1000.00)));
  }

  @Test
  public void shouldNotPaySingleSalariedEmployeeOnWrongDate() throws Exception {
    payData = EmployeeData.getPayDayWrongDataForSalariedEmployee();
    addEmp.addSalariedEmployee(data);
    payEmp.paySingleEmployee(payData);

    Date payDate = EmployeeDataUtils.getPayDate(payData);
    Paycheck pc = getPayCheckForEmployee(payDate);
    assertThat(pc, is(nullValue()));
  }

  @Test
  public void shouldNotPaySingleHourlyEmployeeNoTimeCards() throws Exception {
    payData = EmployeeData.getPayDayDataForHourlyEmployee();
    addEmp.addHourlyEmployee(data);
    payEmp.paySingleEmployee(payData);

    Date payDate = EmployeeDataUtils.getPayDate(payData);
    Paycheck pc = getPayCheckForEmployee(payDate);
    validatePaycheck(pc, getId(), payDate, new BigDecimal("0.00"));
  }

  @Test
  public void shouldPaySingleHourlyEmployeeOneTimeCard() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmployeeData.getTimeCardTwoHoursForEmployee());
    payData = EmployeeData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmployeeDataUtils.getPayDate(payData);
    Paycheck pc = getPayCheckForEmployee(payDate);
    validatePaycheck(pc, getId(), payDate, new BigDecimal("30.48"));
  }

  @Test
  public void shouldPaySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmployeeData.getOvertimeTimeCardForEmployee());
    payData = EmployeeData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);

    Date payDate = EmployeeDataUtils.getPayDate(payData);
    Paycheck pc = getPayCheckForEmployee(payDate);
    final BigDecimal expectedPay = new BigDecimal("144.78"); // (8 + 1.5) * 15.24
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldPaySingleHourlyEmployeeOnWrongDate() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmployeeData.getTimeCardTwoHoursForEmployee());
    payData = EmployeeData.getPayDayWrongDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmployeeDataUtils.getPayDate(payData);

    Paycheck pc = getPayCheckForEmployee(payDate);
    assertThat(pc, is(nullValue()));
  }

  @Test
  public void shouldPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmployeeData.getTimeCardTwoHoursForEmployee());
    addTc.addTimeCard(EmployeeData.getTimeCardFiveHoursForEmployee());
    payData = EmployeeData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmployeeDataUtils.getPayDate(payData);

    Paycheck pc = getPayCheckForEmployee(payDate);
    final BigDecimal expectedPay = new BigDecimal("106.68"); // (2 + 5) * 15.24
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldpaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
    addEmp.addHourlyEmployee(data);
    addTc.addTimeCard(EmployeeData.getTimeCardTwoHoursForEmployee());
    addTc.addTimeCard(EmployeeData.getTimeCardPreviousPeriodForEmployee());
    payData = EmployeeData.getPayDayDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmployeeDataUtils.getPayDate(payData);

    Paycheck pc = getPayCheckForEmployee(payDate);
    final BigDecimal expectedPay = new BigDecimal("30.48"); // 2 * 15.24
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldPaySingleCommissionedEmployeeNoSales() throws Exception {
    addEmp.addCommissionedEmployee(data);
    payData = EmployeeData.getPayDayDataForCommissionedEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmployeeDataUtils.getPayDate(payData);

    Paycheck pc = getPayCheckForEmployee(payDate);
    BigDecimal expectedPay = new BigDecimal("1153.85"); // 2500/mo * (12mo / 26checks)
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldPaySingleCommissionedEmployeeOnWrongDate() throws Exception {
    addEmp.addCommissionedEmployee(data);
    payData = EmployeeData.getPayDayWrongDataForHourlyEmployee();
    payEmp.paySingleEmployee(payData);

    Paycheck pc = getPayCheckForEmployee(EmployeeDataUtils.getPayDate(payData));
    assertThat(pc, is(nullValue()));
  }

  @Test
  public void shouldPaySingleCommissionedEmployeeWithOneSale() throws Exception {
    addEmp.addCommissionedEmployee(data);
    addSR.addSalesReceipt(EmployeeData.getFirstSalesRecieptForEmployee());
    payData = EmployeeData.getPayDayDataForCommissionedEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmployeeDataUtils.getPayDate(payData);

    Paycheck pc = getPayCheckForEmployee(payDate);
    BigDecimal expectedPay = new BigDecimal("1169.85"); // 2500/mo * (12mo / 26checks) + 500.00 * 3.2%
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldPaySingleCommissionedEmployeeWithTwoSales() throws Exception {
    addEmp.addCommissionedEmployee(data);
    addSR.addSalesReceipt(EmployeeData.getFirstSalesRecieptForEmployee());
    addSR.addSalesReceipt(EmployeeData.getSecondSalesRecieptDataForEmployee());
    payData = EmployeeData.getPayDayDataForCommissionedEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmployeeDataUtils.getPayDate(payData);

    Paycheck pc = getPayCheckForEmployee(payDate);
    BigDecimal expectedPay = new BigDecimal("1177.87"); // 2500/mo * (12mo / 26checks) + 750.50 * 3.2%
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldPaySingleCommissionedEmployeeWithSaleInPreviousPayPeriod() throws Exception {
    addEmp.addCommissionedEmployee(data);
    addSR.addSalesReceipt(EmployeeData.getFirstSalesRecieptForEmployee());
    addSR.addSalesReceipt(EmployeeData.getPreviousPeriodSalesRecieptDataForEmployee());
    payData = EmployeeData.getPayDayDataForCommissionedEmployee();
    payEmp.paySingleEmployee(payData);
    Date payDate = EmployeeDataUtils.getPayDate(payData);

    Paycheck pc = getPayCheckForEmployee(payDate);
    BigDecimal expectedPay = new BigDecimal("1169.85"); // 2500/mo * (12mo / 26checks) + 500.00 * 3.2%
    validatePaycheck(pc, getId(), payDate, expectedPay);
  }

  @Test
  public void shouldPaySalariedUnionMemberEmployee() throws Exception {
    addEmp.addSalariedEmployee(data);
    changeEmp.changeToMember(EmployeeData.getUnionMembershipDataForEmployee());
    payEmp.paySingleEmployee(EmployeeData.getPayDayDataForSalariedEmployee());
    Date payDate = EmployeeDataUtils.getPayDate(EmployeeData.getPayDayDataForSalariedEmployee());

    Paycheck pc = getPayCheckForEmployee(payDate);
    validatePaycheckWithDeductions(pc,
                                   payDate,
                                   new BigDecimal("1000.00").setScale(2),
                                   new BigDecimal("952.90"),
                                   new BigDecimal("47.10")); // 1000 - (9.42 * 5)
  }

  @Test
  public void shouldPayHourlyUnionMemberEmployee() throws Exception {

    addEmp.addHourlyEmployee(data);
    changeEmp.changeToMember(EmployeeData.getUnionMembershipDataForEmployee());
    addTc.addTimeCard(EmployeeData.getTimeCardEightHoursForEmployee());
    addSc.addServiceCharge(EmployeeData.getServiceChargeDataForHourlyEmployee());
    payEmp.paySingleEmployee(EmployeeData.getPayDayDataForHourlyEmployee());
    Date payDate = EmployeeDataUtils.getPayDate(EmployeeData.getPayDayDataForHourlyEmployee());
    Paycheck pc = getPayCheckForEmployee(payDate);

    validatePaycheckWithDeductions(pc,
                                   payDate,
                                   new BigDecimal("121.92"),
                                   new BigDecimal("93.08"),
                                   new BigDecimal("28.84"));
  }

  @Test
  public void shouldPayHourlyWithServiceChargesSpanningMultiplePayPeriodsEmployee() throws Exception {
    addEmp.addHourlyEmployee(data);
    changeEmp.changeToMember(EmployeeData.getUnionMembershipDataForEmployee());
    addSc.addServiceCharge(EmployeeData.getServiceChargeDataForHourlyEmployee());
    addSc.addServiceCharge(EmployeeData.getServiceChargeForNextPeriodMapForHourlyEmployee());
    addSc.addServiceCharge(EmployeeData.getServiceChargeForPreviousPeriodForHourlyEmployee());
    addTc.addTimeCard(EmployeeData.getTimeCardEightHoursForEmployee());
    payEmp.paySingleEmployee(EmployeeData.getPayDayDataForHourlyEmployee());

    Date payDate = EmployeeDataUtils.getPayDate(EmployeeData.getPayDayDataForHourlyEmployee());
    Paycheck pc = getPayCheckForEmployee(payDate);
    validatePaycheckWithDeductions(pc,
                                   payDate,
                                   new BigDecimal("121.92"),
                                   new BigDecimal("93.08"),
                                   new BigDecimal("28.84"));
  }

  private int getId() {
    return (int) data.get(Constants.EMPLOYEE_ID.name());
  }

  private Paycheck getPayCheckForEmployee(Date payDate) {
    Employee employee = GpayrollDatabase.getEmployee(getId());
    Paycheck pc = employee.getPaychecks().get(payDate);
    return pc;
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
