package com.georgiev.resolver;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.EmployeeEntity;
import com.georgiev.payroll.entities.HourlyEmployeeEntity;
import com.georgiev.payroll.entities.TimeCardEntity;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.TimeCard;
import java.util.HashSet;
import java.util.Set;

public class HourlyEmployeeCreator extends EmployeeCreator {

  @Override
  EmployeeEntity createEmployeeEntity(Employee employee) {
    if (employee.getPayType() instanceof Hourly) {
      HourlyEmployeeEntity entity = new HourlyEmployeeEntity();
      initEmployeeEntity(employee, entity);
      Hourly hourly = (Hourly) employee.getPayType();

      Set<TimeCardEntity> timecardEntities = new HashSet<TimeCardEntity>();
      for (TimeCard tc : hourly.getTimeCards().values()) {
        TimeCardEntity tce = new TimeCardEntity();
        tce.setDate(tc.getDate());
        tce.setHours(tc.getHours());
        tce.setEmployee(entity);
        timecardEntities.add(tce);
      }
      entity.setHourlyRate(hourly.getHourlyRate());
      entity.setTimecards(timecardEntities);
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
