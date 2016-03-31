package com.georgiev.usecases.factory;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.usecases.UseCase;

public interface AddEmployeeUseCaseFactory {

  UseCase makeAddCommissionedEmployee(PayrollDatabase db);

  UseCase makeAddSalariedEmployee(PayrollDatabase db);

  UseCase makeAddHourlyEmployee(PayrollDatabase db);

}
