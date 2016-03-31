package com.georgiev.resolver;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.EmployeeEntity;
import com.georgiev.payroll.entities.SalariedEmployeeEntity;
import com.georgiev.payroll.impl.Salaried;

public class SalariedEmployeeCreator extends EmployeeCreator {

  @Override
  EmployeeEntity createEmployeeEntity(Employee employee) {
    if (employee.getPayType() instanceof Salaried) {
      SalariedEmployeeEntity entity = new SalariedEmployeeEntity();
      initEmployeeEntity(employee, entity);
      Salaried payType = (Salaried) employee.getPayType();
      entity.setSalary(payType.getSalary());
      return entity;
    }
    else if (successor != null) {
      return successor.createEmployeeEntity(employee);
    }
    else {
      throw new RuntimeException("The Successor is Not SET");
    }
  }

}
