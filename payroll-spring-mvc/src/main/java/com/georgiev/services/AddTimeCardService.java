package com.georgiev.services;

import com.georgiev.payroll.db.PayrollDatabase;
import java.util.Map;

public interface AddTimeCardService {

  String addTimeCard(Map<String, Object> data, PayrollDatabase payrollDatabase);

}
