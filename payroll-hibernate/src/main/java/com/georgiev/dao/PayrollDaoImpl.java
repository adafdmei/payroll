package com.georgiev.dao;

import HibernateUtil.HibernateUtil;
import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.entities.EmployeeEntity;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

// @Repository("payrollDao")
public class PayrollDaoImpl extends AbstractDao<Integer, EmployeeEntity>implements PayrollDatabase {

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

  @Override
  public void addEmployee(Employee employee) {
    EmployeeEntity emp = new EmployeeEntity();
    emp.setAddress("adre");
    emp.setName("name");
    emp.setType("tet");

    //Get Session
    SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
    Session session = sessionFactory.getCurrentSession();
    //start transaction
    session.beginTransaction();
    //Save the Model object
    session.save(emp);
    //Commit transaction
    session.getTransaction().commit();

    //terminate session factory, otherwise program won't end
    sessionFactory.close();

    persist(emp);

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
