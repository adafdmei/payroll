package com.georgiev.services;

import com.georgiev.payroll.db.PayrollDatabase;
import java.util.Map;

public interface ChangeEmplyeeService {

  String changeEmplyeeName(Map<String, Object> data, PayrollDatabase payrollDatabase);

  String changeEmplyeeToCommissioned(Map<String, Object> data, PayrollDatabase payrollDatabase);

  String changeEmplyeeToHourly(Map<String, Object> data, PayrollDatabase payrollDatabase);

  String changeEmplyeeToSalaried(Map<String, Object> data, PayrollDatabase payrollDatabase);
}
