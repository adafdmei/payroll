package com.georgiev.payroll.transaction.impl;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.Affiliation;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PaymentClassification;
import com.georgiev.payroll.domain.PaymentMethod;
import com.georgiev.payroll.domain.PaymentSchedule;
import com.georgiev.payroll.impl.HoldMethod;
import com.georgiev.payroll.impl.NoAffiliation;
import com.georgiev.payroll.transaction.Transaction;

public abstract class AddEmployeeTransaction implements Transaction {

  private final int employeeId;
  private final String name;
  private final String address;

  public AddEmployeeTransaction(int employeeId, String name, String address) {
    this.employeeId = employeeId;
    this.name = name;
    this.address = address;
  }

  @Override
  public final void execute() {
    PaymentClassification pc = getClassification();
    PaymentSchedule ps = getSchedule();
    PaymentMethod pm = new HoldMethod();
    Affiliation af = new NoAffiliation();
    Employee e = new Employee(employeeId, name, address);
    e.setClassification(pc);
    e.setSchedule(ps);
    e.setMethod(pm);
    e.setAffiliation(af);
    GpayrollDatabase.addEmployee(employeeId, e);
  }

  protected abstract PaymentClassification getClassification();

  protected abstract PaymentSchedule getSchedule();

}
