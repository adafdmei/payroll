package com.georgiev.dao;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.HourlyEmployeeEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("payrollDao")
//public class PayrollDaoImpl implements PayrollDatabase, JpaRepository<HourlyEmployeeEntity, Integer> {

public class PayrollDaoImpl2 extends AbstractDao<Integer, HourlyEmployeeEntity>implements PayrollDatabase {

  @Override
  public void addEmployee(Employee employee) {
    //EmployeeEntity emp = new EmployeeEntity();
    HourlyEmployeeEntity emp = new HourlyEmployeeEntity();
    emp.setAddress("adre");
    emp.setName("name");
    emp.setType("tet");
    emp.setHourlyRate(BigDecimal.ONE);
    getSession().persist(emp);

    //start transaction
    //Session session = getSession();
    //session.beginTransaction();
    //Save the Model object
    //session.save(emp);
    //Commit transaction
    //session.getTransaction().commit();
    //System.out.println("Employee ID=" + emp.getId());

    //terminate session factory, otherwise program won't end

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
  public Employee getEmployee(int employeeId) {
    // TODO Auto-generated method stub
    return null;
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
