package com.georgiev.usecases;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.domain.PayDisposition;
import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.impl.HoldMethod;
import com.georgiev.payroll.impl.NoMember;
import com.georgiev.payroll.request.AddEmployeeRequest;
import com.georgiev.payroll.request.Request;

public abstract class AddEmployeeUseCase implements UseCase {

  @Override
  public void execute(Request request) {
    PayDisposition pm = new HoldMethod();
    UnionMembership af = new NoMember();
    AddEmployeeRequest aepr = (AddEmployeeRequest) request;
    Employee e = new Employee(aepr.getEmployeeId(), aepr.getName(), aepr.getAddress());
    e.setPayType(getClassification(request));
    e.setPaySchedule(getSchedule());
    e.setMethod(pm);
    e.setUnionMembership(af);
    GpayrollDatabase.addEmployee(e.getEmployeeId(), e);
  }

  protected abstract AbstractPayType getClassification(Request request);

  public abstract PaySchedule getSchedule();

}