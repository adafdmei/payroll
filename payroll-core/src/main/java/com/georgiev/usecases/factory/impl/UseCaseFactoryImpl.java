package com.georgiev.usecases.factory.impl;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
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
import com.georgiev.usecases.GetAllEmployeeUseCase;
import com.georgiev.usecases.PaydayUseCase;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.UseCaseFactory;

public class UseCaseFactoryImpl implements UseCaseFactory {

  private final PayrollDatabase payrollDatabase = new InMemoryPayrollDatabase();

  @Override
  public UseCase makeAddCommissionedEmployee() {
    return new AddCommissionedEmployeeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeAddSalariedEmployee() {
    return new AddSalariedEmployeeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeAddHourlyEmployee() {
    return new AddHourlyEmployeeUseCase(payrollDatabase);
  }

  @Override
  public UseCase makeDeleteEmployee() {
    return new DeleteEmployeeUseCase();
  }

  @Override
  public UseCase makeAddTimeCard() {
    return new AddTimeCardUseCase();
  }

  @Override
  public UseCase makeAddSalesReciept() {
    return new AddSalesRecieptUseCase();
  }

  @Override
  public UseCase makeChangeEmployeeName() {
    return new ChangeEmployeeNameUseCase();
  }

  @Override
  public UseCase makeChangeEmployeeHourly() {
    return new ChangeHourlyUseCase();
  }

  @Override
  public UseCase makeChangeEmployeeSalaried() {
    return new ChangeSalariedUseCase();
  }

  @Override
  public UseCase makeChangeEmployeeCommissioned() {
    return new ChangeCommissionedUseCase();
  }

  @Override
  public UseCase makeChangeEmployeeMember() {
    return new ChangeEmployeeMemberUseCase();
  }

  @Override
  public UseCase makeChangeEmployeeNoMember() {
    return new ChangeEmployeeNoMemberUseCase();
  }

  @Override
  public UseCase makeAddServiceCharge() {
    return new AddServiceChargeUseCase();
  }

  @Override
  public UseCase makePayDay() {
    return new PaydayUseCase();
  }

  @Override
  public UseCase makeFindEmployee() {
    return new FindAnEmployeeUseCase();
  }

  @Override
  public UseCase makeFindAllEmployees() {
    return new GetAllEmployeeUseCase();
  }
}
