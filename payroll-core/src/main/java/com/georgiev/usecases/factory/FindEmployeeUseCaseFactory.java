package com.georgiev.usecases.factory;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.usecases.UseCase;

public interface FindEmployeeUseCaseFactory {

  UseCase makeFindAllEmployees(PayrollDatabase payrollDatabase);

  UseCase makeFindEmployee(PayrollDatabase payrollDatabase);

}
