package com.georgiev.services.impl;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.services.DataBaseProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataBaseProviderService")
public class DataBaseProviderServiceImpl implements DataBaseProviderService {

  @Autowired
  PayrollDatabase payrollDatabaseService;

  @Override
  public PayrollDatabase getDataBaseImpl() {
    return payrollDatabaseService;
  }

}
