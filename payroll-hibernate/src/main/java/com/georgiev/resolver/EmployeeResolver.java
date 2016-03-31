package com.georgiev.resolver;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.EmployeeEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeResolver {

  SalariedEmployeeCreator sCreator;
  HourlyEmployeeCreator hCreator;
  CommissionedEmployeeCreator cCreator;

  public EmployeeResolver() {
    sCreator = new SalariedEmployeeCreator();
    hCreator = new HourlyEmployeeCreator();
    cCreator = new CommissionedEmployeeCreator();
    sCreator.setSuccessor(hCreator);
    hCreator.setSuccessor(cCreator);
  }

  public EmployeeEntity resolveEmloyee(Employee employee) {
    return sCreator.createEmployeeEntity(employee);
  }

}
