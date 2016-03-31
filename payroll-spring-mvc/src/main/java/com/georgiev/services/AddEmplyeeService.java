package com.georgiev.services;

import com.georgiev.payroll.db.PayrollDatabase;
import java.util.Map;

public interface AddEmplyeeService {

  String addSalariedEmpolyee(Map<String, Object> data, PayrollDatabase payrollDatabase);

  String addCommisionedEmployee(Map<String, Object> data, PayrollDatabase payrollDatabase);

  String addHourlyEmployee(Map<String, Object> data, PayrollDatabase payrollDatabase);

}
