package com.georgiev.services;

import java.util.Map;

public interface ChangeEmplyeeService {

  String changeEmplyeeName(Map<String, Object> data);

  String changeEmplyeeToCommissioned(Map<String, Object> data);

  String changeEmplyeeToHourly(Map<String, Object> data);

  String changeEmplyeeToSalaried(Map<String, Object> data);
}
