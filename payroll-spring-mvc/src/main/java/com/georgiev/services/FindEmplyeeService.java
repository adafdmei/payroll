package com.georgiev.services;

import java.util.Map;

public interface FindEmplyeeService {

  Map<String, Object> findAllEmployees();

  Map<String, Object> findEmployee(Map<String, Object> data);
}
