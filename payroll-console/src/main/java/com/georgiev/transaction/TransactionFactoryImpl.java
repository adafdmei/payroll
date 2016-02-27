package com.georgiev.transaction;

import com.georgiev.payroll.request.AddSalesRequest;
import com.georgiev.payroll.request.AddServiceChargeRequest;
import com.georgiev.payroll.request.AddTimeCardRequest;
import com.georgiev.payroll.request.ChangeCommissionedRequest;
import com.georgiev.payroll.request.ChangeEmployeeNameRequest;
import com.georgiev.payroll.request.ChangeHourlyRequest;
import com.georgiev.payroll.request.ChangeMemberRequest;
import com.georgiev.payroll.request.ChangeSalariedRequest;
import com.georgiev.payroll.request.ChangeNoMemberRequest;
import com.georgiev.payroll.request.PaydayRequest;
import com.georgiev.payroll.request.PayrollRequest;
import com.georgiev.payroll.transaction.UseCase;
import com.georgiev.payroll.transaction.TransactionFactory;
import com.georgiev.payroll.transaction.impl.AddCommissionedEmployeeUseCase;
import com.georgiev.payroll.transaction.impl.AddHourlyEmployee;
import com.georgiev.payroll.transaction.impl.AddSalariedEmployee;
import com.georgiev.usecases.DeleteEmployeeUseCase;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionFactoryImpl implements TransactionFactory {

  @Override
  public UseCase makeAddCommissionedTransaction(PayrollRequest requst) {
    return new AddCommissionedEmployeeUseCase(requst);
  }

  @Override
  public UseCase makeAddHourlyTransaction(int employeeId,
                                              String name,
                                              String address,
                                              BigDecimal hourlyRate) {
    return new AddHourlyEmployee(employeeId, name, address, hourlyRate);
  }

  @Override
  public UseCase makeAddSalariedTransaction(int employeeId, String name, String address, BigDecimal salary) {
    return new AddSalariedEmployee(employeeId, name, address, salary);
  }

  @Override
  public UseCase makeChangeCommissionedTransaction(int employeeId,
                                                       BigDecimal salary,
                                                       BigDecimal commissionRate) {
    return new ChangeCommissionedRequest(employeeId, salary, commissionRate);
  }

  @Override
  public UseCase makeChangeHourlyTransaction(int employeeId, BigDecimal hourlyRate) {
    return new ChangeHourlyRequest(employeeId, hourlyRate);
  }

  @Override
  public UseCase makeChangeMemberTransaction(int employeeId, int memberId, BigDecimal weeklyDues) {
    return new ChangeMemberRequest(employeeId, memberId, weeklyDues);
  }

  @Override
  public UseCase makeChangeNameTransaction(int employeeId, String name) {
    return new ChangeEmployeeNameRequest(employeeId, name);
  }

  @Override
  public UseCase makeChangeSalariedTransaction(int employeeId, BigDecimal salary) {
    return new ChangeSalariedRequest(employeeId, salary);
  }

  @Override
  public UseCase makeChangeUnaffiliatedTransaction(int employeeId) {
    return new ChangeNoMemberRequest(employeeId);
  }

  @Override
  public UseCase makeDeleteEmployeeTransaction(int employeeId) {
    return new DeleteEmployeeUseCase(employeeId);
  }

  @Override
  public UseCase makePaydayTransaction(Date payDate) {
    return new PaydayRequest(payDate);
  }

  @Override
  public UseCase makeSalesReceiptTransaction(Date date, BigDecimal amount, int employeeId) {
    return new AddSalesRequest(date, amount, employeeId);
  }

  @Override
  public UseCase makeServiceChargeTransaction(int memberId, Date date, BigDecimal charge) {
    return new AddServiceChargeRequest(memberId, date, charge);
  }

  @Override
  public UseCase makeTimeCardTransaction(Date date, BigDecimal hours, int employeeId) {
    return new AddTimeCardRequest(date, hours, employeeId);
  }

}
