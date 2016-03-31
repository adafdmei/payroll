package com.georgiev.resolver;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.EmployeeEntity;
import com.georgiev.payroll.entities.HourlyEmployeeEntity;
import com.georgiev.payroll.impl.Hourly;

public class HourlyEmployeeCreator extends EmployeeCreator {

  @Override
  EmployeeEntity createEmployeeEntity(Employee employee) {
    if (employee.getPayType() instanceof Hourly) {
      HourlyEmployeeEntity entity = new HourlyEmployeeEntity();
      initEmployeeEntity(employee, entity);
      Hourly payType = (Hourly) employee.getPayType();
      entity.setHourlyRate(payType.getHourlyRate());
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
