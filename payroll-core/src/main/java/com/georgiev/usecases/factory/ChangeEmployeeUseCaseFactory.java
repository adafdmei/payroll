package com.georgiev.usecases.factory;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.usecases.UseCase;

public interface ChangeEmployeeUseCaseFactory {

  UseCase makeChangeEmployeeName(PayrollDatabase payrollDatabase);

  UseCase makeChangeEmployeeHourly(PayrollDatabase payrollDatabase);

  UseCase makeChangeEmployeeSalaried(PayrollDatabase payrollDatabase);

  UseCase makeChangeEmployeeCommissioned(PayrollDatabase payrollDatabase);

  UseCase makeChangeEmployeeMember(PayrollDatabase payrollDatabase);

  UseCase makeChangeEmployeeNoMember(PayrollDatabase payrollDatabase);

}
