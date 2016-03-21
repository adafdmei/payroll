package com.georgiev.usecases.factory;

import com.georgiev.usecases.UseCase;

public interface FindEmployeeUseCaseFactory {

  UseCase makeGetAllEmployees();

  UseCase makeFindAnEmployee();

}
