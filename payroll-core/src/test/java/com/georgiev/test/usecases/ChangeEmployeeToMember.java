package com.georgiev.test.usecases;

import com.georgiev.builder.ChangeEmployeeRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.ChangeEmployeeUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;

public class ChangeEmployeeToMember {

  private final ChangeEmployeeRequestBuilder changeEmpRequestBuilder;
  private final ChangeEmployeeUseCaseFactory changeEmpFactory;

  public ChangeEmployeeToMember() {
    changeEmpRequestBuilder = new RequestBuilderImpl();
    changeEmpFactory = new UseCaseFactoryImpl();
  }

  public void changeToMember(PayrollDatabase db, Map<String, Object> data) {
    Request request = changeEmpRequestBuilder.buildChangeMemberRequest(data);
    UseCase changeEmployeeMemberUseCase = changeEmpFactory.makeChangeEmployeeMember(db);
    changeEmployeeMemberUseCase.execute(request);
  }

}