package com.georgiev.services;

import com.georgiev.payroll.db.PayrollDatabase;

public interface DataBaseProviderService {

  PayrollDatabase getDataBaseImpl();
}
