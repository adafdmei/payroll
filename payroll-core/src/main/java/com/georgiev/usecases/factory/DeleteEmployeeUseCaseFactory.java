package com.georgiev.usecases.factory;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.usecases.UseCase;

public interface DeleteEmployeeUseCaseFactory {

  UseCase makeDeleteEmployee(PayrollDatabase payrollDatabase);
}
