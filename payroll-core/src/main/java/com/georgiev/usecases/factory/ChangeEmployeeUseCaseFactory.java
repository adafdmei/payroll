package com.georgiev.usecases.factory;

import com.georgiev.usecases.UseCase;

public interface ChangeEmployeeUseCaseFactory {
  UseCase makeChangeEmployeeName();

  UseCase makeChangeEmployeeHourly();

  UseCase makeChangeEmployeeSalaried();

  UseCase makeChangeEmployeeCommissioned();

  UseCase makeChangeEmployeeMember();

  UseCase makeChangeEmployeeNoMember();

}
