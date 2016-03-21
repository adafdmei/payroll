package com.georgiev.usecases.factory;

import com.georgiev.usecases.UseCase;

public interface AddEmployeeUseCaseFactory {

  UseCase makeAddCommissionedEmployee();

  UseCase makeAddSalariedEmployee();

  UseCase makeAddHourlyEmployee();

}
