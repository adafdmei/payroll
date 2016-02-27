package com.georgiev.usecases.factory;

import com.georgiev.usecases.UseCase;

public interface AddEmployeeUseCaseFactory {

  UseCase makeAddCommisionedEmployee();

  UseCase makeAddSalariedEmployee();

  UseCase makeAddHourlyEmployee();

}
