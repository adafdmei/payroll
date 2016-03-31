package com.georgiev.resolver;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.EmployeeEntity;

public abstract class EmployeeCreator {

  protected EmployeeCreator successor;

  public void setSuccessor(EmployeeCreator successor) {
    this.successor = successor;
  }

  protected void initEmployeeEntity(Employee employee, EmployeeEntity entity) {
    entity.setName(employee.getName());
    entity.setAddress(employee.getAddress());
    entity.setId(employee.getEmployeeId());
    entity.setType(employee.getPayType().getClass().getSimpleName());
  }

  abstract EmployeeEntity createEmployeeEntity(Employee employee);

}
