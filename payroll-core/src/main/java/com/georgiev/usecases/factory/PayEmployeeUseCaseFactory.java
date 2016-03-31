package com.georgiev.usecases.factory;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.usecases.UseCase;

public interface PayEmployeeUseCaseFactory {

  UseCase makePayDay(PayrollDatabase payrollDatabase);
}
