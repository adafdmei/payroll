package com.georgiev.payroll.db;

import com.georgiev.payroll.domain.Employee;
import java.util.List;
import java.util.Map;

public interface PayrollDatabase {

  /**
   * @deprecated, inject implementation class via factory
   */
  @Deprecated
  class GlobalInstance {
    @Deprecated
    public static PayrollDatabase GpayrollDatabase;

  }

  void clear();

  void addEmployee(Employee employee);

  void deleteEmployee(int employeeId);

  Employee getEmployee(int employeeId);

  Map<String, Employee> getAllEmployees();

  List<Integer> getAllEmployeeIds();

  void addUnionMember(int memberId, Employee employee);

  void deleteUnionMember(int memberId);

  Employee getUnionMember(int memberId);

  Employee getEmployeeWithTimeCards(int employeeId);

  void sync(Employee employee);

}
