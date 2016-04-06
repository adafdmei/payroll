package com.georgiev.dao;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.EmployeeEntity;
import com.georgiev.payroll.entities.HourlyEmployeeEntity;
import com.georgiev.payroll.entities.TimeCardEntity;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.TimeCard;
import com.georgiev.resolver.EmployeeResolver;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("payrollDatabaseService")
public class PayrollDatabaseImpl implements PayrollDatabase {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  EmployeeResolver employeeResolver;

  EmployeeEntity entity = new EmployeeEntity();

  @Override
  public void addEmployee(Employee employee) {
    entity = employeeResolver.resolveEmloyee(employee);
    employeeRepository.save(entity);
  }

  @Override
  public Employee getEmployee(int employeeId) {
    if (entity.getId() != employeeId) {
      entity = employeeRepository.findOne(employeeId);
    }
    return new Employee(entity.getId(), entity.getName(), entity.getAddress());
  }

  @Override
  public Employee getEmployeeWithTimeCards(int employeeId) {
    entity = employeeRepository.findOne(employeeId);
    HourlyEmployeeEntity hee = (HourlyEmployeeEntity) entity;
    Set<TimeCardEntity> timecards = hee.getTimecards();

    Employee employee = new Employee(entity.getId(), entity.getName(), entity.getAddress());
    Hourly hourly = new Hourly(((HourlyEmployeeEntity) entity).getHourlyRate());
    for (TimeCardEntity tce : timecards) {
      TimeCard tc = new TimeCard(tce.getDate(), tce.getHours());
      hourly.addTimeCard(tc);
    }
    employee.setPayType(hourly);

    return employee;
  }

  @Override
  public void clear() {

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

  @Override
  public void sync(Employee employee) {
    //demo
    HourlyEmployeeEntity hee = (HourlyEmployeeEntity) entity;
    TimeCardEntity tce = new TimeCardEntity();
    tce.setDate(new Date());
    tce.setHours(BigDecimal.ONE);
    tce.setEmployee(hee);
    hee.getTimecards().add(tce);

  }
}
