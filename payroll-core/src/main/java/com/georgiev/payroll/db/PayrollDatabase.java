package com.georgiev.payroll.db;

import java.util.List;
import java.util.Map;

import com.georgiev.payroll.domain.Employee;

public interface PayrollDatabase {

  class GlobalInstance {
    public static PayrollDatabase GpayrollDatabase;
  }

  void clear();

  void addEmployee(int employeeId, Employee employee);

  void deleteEmployee(int employeeId);

  Employee getEmployee(int employeeId);

  Map<String, Employee> getAllEmployees();

  List<Integer> getAllEmployeeIds();

  void addUnionMember(int memberId, Employee employee);

  void deleteUnionMember(int memberId);

  Employee getUnionMember(int memberId);

}
