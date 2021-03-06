package com.georgiev.usecases.factory;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.usecases.UseCase;

public interface AddTimeCardUseCaseFactory {

  UseCase makeAddTimeCard(PayrollDatabase payrollDatabase);

}
