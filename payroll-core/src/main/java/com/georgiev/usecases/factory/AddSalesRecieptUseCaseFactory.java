package com.georgiev.usecases.factory;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.usecases.UseCase;

public interface AddSalesRecieptUseCaseFactory {

  UseCase makeAddSalesReciept(PayrollDatabase payrollDatabase);

}
