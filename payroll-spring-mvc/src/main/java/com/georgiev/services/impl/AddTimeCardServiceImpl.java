package com.georgiev.services.impl;

import com.georgiev.builder.AddTimeCardRequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.services.AddTimeCardService;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.AddTimeCardUseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service("addTimeCardService")
public class AddTimeCardServiceImpl implements AddTimeCardService {

  AddTimeCardRequestBuilder requestBuilder;
  AddTimeCardUseCaseFactory useCaseFactory;

  @PostConstruct
  private void init() {
    requestBuilder = new RequestBuilderImpl();
    useCaseFactory = new UseCaseFactoryImpl();
  }

  @Transactional
  @Override
  public String addTimeCard(Map<String, Object> data, PayrollDatabase payrollDatabase) {
    Request request = requestBuilder.buildAddTimeCardRequest(data);
    UseCase useCase = useCaseFactory.makeAddTimeCard(payrollDatabase);
    execute(request, useCase);
    return response(useCase);
  }

  private String response(UseCase useCase) {
    return useCase.getResponse().getAsString();
  }

  private void execute(Request request, UseCase useCase) {
    useCase.execute(request);
  }

}
