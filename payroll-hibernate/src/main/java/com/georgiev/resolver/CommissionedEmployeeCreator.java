package com.georgiev.resolver;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.CommissionedEmployeeEntity;
import com.georgiev.payroll.entities.EmployeeEntity;
import com.georgiev.payroll.impl.Commissioned;

public class CommissionedEmployeeCreator extends EmployeeCreator {

  @Override
  EmployeeEntity createEmployeeEntity(Employee employee) {
    if (employee.getPayType() instanceof Commissioned) {
      CommissionedEmployeeEntity entity = new CommissionedEmployeeEntity();
      initEmployeeEntity(employee, entity);
      Commissioned payType = (Commissioned) employee.getPayType();
      entity.setCommissionRate(payType.getCommissionRate());
      entity.setBasePay(payType.getBasePay());
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
