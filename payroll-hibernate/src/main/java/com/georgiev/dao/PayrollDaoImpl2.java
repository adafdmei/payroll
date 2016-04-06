package com.georgiev.dao;

import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.HourlyEmployeeEntity;
import java.math.BigDecimal;

// @Repository("payrollDao")
// public class PayrollDaoImpl implements PayrollDatabase,
// JpaRepository<HourlyEmployeeEntity, Integer> {

public class PayrollDaoImpl2 extends AbstractDao<Integer, HourlyEmployeeEntity> {

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

}
