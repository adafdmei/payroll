package com.georgiev;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.georgiev.payroll.request.PayrollRequest;
import com.georgiev.payroll.transaction.UseCase;
import com.georgiev.payroll.transaction.TransactionFactory;
import com.georgiev.payroll.transaction.source.TextParserSource;

public class TextParserTransactionSourceTest {

  private final UseCase addHourlyTransaction = new TestTransaction();
  private final UseCase addCommissionedTransaction = new TestTransaction();

  private TextParserSource source;

  @Test
  public void addCommissionedEmployee() throws Exception {
    given("AddEmp, 2, Bill, Work, C, 2500.00, 3.2");
    assertThat(source.getDataArgs(), is(addCommissionedTransaction));
  }

  @Ignore
  @Test
  public void addHourlyEmployee() throws Exception {
    given("AddEmp, 3, Lance, Home, H ,15.25");
    assertThat(source.getDataArgs(), is(addHourlyTransaction));
  }

  @Ignore
  @Test 
  public void canReadMultipleTransactions() throws Exception {
    given("AddEmp, 2, Bill, Work, C, 2500.00, 3.2" + "\n AddEmp, 3, Lance, Home, H, 15.25");
    assertThat(source.getDataArgs(), is(addCommissionedTransaction));
    assertThat(source.getDataArgs(), is(addHourlyTransaction));
    assertThat(source.getDataArgs(), is(nullValue()));
  }

  private void given(String transactions) throws Exception {
    source = new TextParserSource(new TestTransactionFactory(),
                                             transactions);
  }

  private class TestTransaction implements UseCase {

    @Override
    public void execute() {
    }

  }

  private class TestTransactionFactory implements TransactionFactory {

    @Override
    public UseCase makeAddCommissionedTransaction(PayrollRequest request) {
      assertThat(request.getId(), is(2));
      assertThat(request.getName(), is("Bill"));
      assertThat(request.getAddress(), is("Work"));
      assertThat(request.getSalary(), is(new BigDecimal("2500.00")));
      assertThat(request.getCommissionRate(), is(new BigDecimal("3.2")));
      return addCommissionedTransaction;
    }

    @Override
    public UseCase makeAddHourlyTransaction(int employeeId,
                                                String name,
                                                String address,
                                                BigDecimal hourlyRate) {
      assertThat(employeeId, is(3));
      assertThat(name, is("Lance"));
      assertThat(address, is("Home"));
      assertThat(hourlyRate, is(new BigDecimal("15.25")));
      return addHourlyTransaction;
    }

    @Override
    public UseCase makeAddSalariedTransaction(int employeeId,
                                                  String name,
                                                  String address,
                                                  BigDecimal salary) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeChangeCommissionedTransaction(int employeeId,
                                                         BigDecimal salary,
                                                         BigDecimal commissionRate) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeChangeHourlyTransaction(int employeeId, BigDecimal hourlyRate) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeChangeMemberTransaction(int employeeId, int memberId, BigDecimal weeklyDues) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeChangeNameTransaction(int employeeId, String name) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeChangeSalariedTransaction(int employeeId, BigDecimal salary) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeChangeUnaffiliatedTransaction(int employeeId) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeDeleteEmployeeTransaction(int employeeId) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makePaydayTransaction(Date payDate) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeSalesReceiptTransaction(Date date, BigDecimal amount, int employeeId) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeServiceChargeTransaction(int memberId, Date date, BigDecimal charge) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public UseCase makeTimeCardTransaction(Date date, BigDecimal hours, int employeeId) {
      // TODO Auto-generated method stub
      return null;
    }

  }
}
