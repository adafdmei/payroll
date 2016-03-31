package com.georgiev.services;

import com.georgiev.payroll.db.PayrollDatabase;
import java.util.Map;

public interface FindEmplyeeService {

  Map<String, Object> findAllEmployees(PayrollDatabase payrollDatabase);

  Map<String, Object> findEmployee(Map<String, Object> data, PayrollDatabase payrollDatabase);

}
