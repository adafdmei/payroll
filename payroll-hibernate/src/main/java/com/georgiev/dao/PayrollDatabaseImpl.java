package com.georgiev.dao;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.EmployeeEntity;
import com.georgiev.resolver.EmployeeResolver;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("payrollDatabaseService")
public class PayrollDatabaseImpl implements PayrollDatabase {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  EmployeeResolver employeeResolver;

  @Override
  public void addEmployee(Employee employee) {
    employeeRepository.save(employeeResolver.resolveEmloyee(employee));
  }

  @Override
  public Employee getEmployee(int employeeId) {
    EmployeeEntity entity = employeeRepository.findOne(1);
    Employee employee = new Employee(entity.getId(), entity.getName(), entity.getAddress());
    //employee.se

    return null;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteEmployee(int employeeId) {
    // TODO Auto-generated method stub

  }

  @Override
  public Map<String, Employee> getAllEmployees() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Integer> getAllEmployeeIds() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addUnionMember(int memberId, Employee employee) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteUnionMember(int memberId) {
    // TODO Auto-generated method stub

  }

  @Override
  public Employee getUnionMember(int memberId) {
    // TODO Auto-generated method stub
    return null;
  }

}
