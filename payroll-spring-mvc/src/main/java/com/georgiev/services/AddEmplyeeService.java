package com.georgiev.services;

import java.util.Map;

public interface AddEmplyeeService {

  String addSalariedEmpolyee(Map<String, Object> data);

  String addCommisionedEmployee(Map<String, Object> data);

  String addHourlyEmployee(Map<String, Object> data);

}
