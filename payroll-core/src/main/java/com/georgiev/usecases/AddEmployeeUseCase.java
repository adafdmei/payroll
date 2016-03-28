package com.georgiev.usecases;

import com.georgiev.payroll.db.PayrollDatabase;
import com.georgiev.payroll.domain.AbstractPayType;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.domain.PayDisposition;
import com.georgiev.payroll.domain.PaySchedule;
import com.georgiev.payroll.domain.UnionMembership;
import com.georgiev.payroll.impl.HoldMethod;
import com.georgiev.payroll.impl.NoMember;
import com.georgiev.payroll.request.AddEmployeeRequest;
import com.georgiev.payroll.request.Request;

public abstract class AddEmployeeUseCase implements UseCase {

  private final PayrollDatabase payrollDatabase;
  Response response;

  public AddEmployeeUseCase(PayrollDatabase payrollDatabase) {
    this.payrollDatabase = payrollDatabase;
  }

  @Override
  public void execute(Request request) {
    PayDisposition pm = new HoldMethod();
    UnionMembership af = new NoMember();
    AddEmployeeRequest aepr = (AddEmployeeRequest) request;
    Employee e = new Employee(aepr.getEmployeeId(), aepr.getName(), aepr.getAddress());
    e.setPayType(getPayType(request));
    e.setPaySchedule(getSchedule());
    e.setMethod(pm);
    e.setUnionMembership(af);
    //GpayrollDatabase.addEmployee(e.getEmployeeId(), e);
    payrollDatabase.addEmployee(e.getEmployeeId(), e);
    response = new MessageResponse("OK");

  }

  protected abstract AbstractPayType getPayType(Request request);

  public abstract PaySchedule getSchedule();

  @Override
  public Response getResponse() {
    return response;
  }
}
