package com.georgiev.usecases.factory.impl;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.usecases.AddCommissionedEmployeeUseCase;
import com.georgiev.usecases.AddHourlyEmployeeUseCase;
import com.georgiev.usecases.AddSalariedEmployeeUseCase;
import com.georgiev.usecases.AddSalesRecieptUseCase;
import com.georgiev.usecases.AddServiceChargeUseCase;
import com.georgiev.usecases.AddTimeCardUseCase;
import com.georgiev.usecases.ChangeCommissionedUseCase;
import com.georgiev.usecases.ChangeEmployeeMemberUseCase;
import com.georgiev.usecases.ChangeEmployeeNameUseCase;
import com.georgiev.usecases.ChangeEmployeeNoMemberUseCase;
import com.georgiev.usecases.ChangeHourlyUseCase;
import com.georgiev.usecases.ChangeSalariedUseCase;
import com.georgiev.usecases.DeleteEmployeeUseCase;
import com.georgiev.usecases.FindAnEmployeeUseCase;
import com.georgiev.usecases.FindAllEmployeeUseCase;
import com.georgiev.usecases.PaydayUseCase;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.UseCaseFactory;

public class UseCaseFactoryImpl implements UseCaseFactory {

  @Override
  public UseCase makeAddCommissionedEmployee(PayrollDatabase payrollDatabase) {
    return new AddCommissionedEmployeeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeAddSalariedEmployee(PayrollDatabase payrollDatabase) {
    return new AddSalariedEmployeeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeAddHourlyEmployee(PayrollDatabase payrollDatabase) {
    return new AddHourlyEmployeeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeDeleteEmployee(PayrollDatabase payrollDatabase) {
    return new DeleteEmployeeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeAddTimeCard(PayrollDatabase payrollDatabase) {
    return new AddTimeCardUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeAddSalesReciept(PayrollDatabase payrollDatabase) {
    return new AddSalesRecieptUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeChangeEmployeeName(PayrollDatabase payrollDatabase) {
    return new ChangeEmployeeNameUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeChangeEmployeeHourly(PayrollDatabase payrollDatabase) {
    return new ChangeHourlyUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeChangeEmployeeSalaried(PayrollDatabase payrollDatabase) {
    return new ChangeSalariedUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeChangeEmployeeCommissioned(PayrollDatabase payrollDatabase) {
    return new ChangeCommissionedUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeChangeEmployeeMember(PayrollDatabase payrollDatabase) {
    return new ChangeEmployeeMemberUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeChangeEmployeeNoMember(PayrollDatabase payrollDatabase) {
    return new ChangeEmployeeNoMemberUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeAddServiceCharge(PayrollDatabase payrollDatabase) {
    return new AddServiceChargeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makePayDay(PayrollDatabase payrollDatabase) {
    return new PaydayUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeFindEmployee(PayrollDatabase payrollDatabase) {
    return new FindAnEmployeeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeFindAllEmployees(PayrollDatabase payrollDatabase) {
    return new FindAllEmployeeUseCase(payrollDatabase);
  }
}
