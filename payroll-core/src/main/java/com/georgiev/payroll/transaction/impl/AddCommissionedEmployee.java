package com.georgiev.payroll.transaction.impl;

import com.georgiev.payroll.domain.PaymentClassification;
import com.georgiev.payroll.domain.PaymentSchedule;
import com.georgiev.payroll.impl.BiweeklySchedule;
import com.georgiev.payroll.impl.CommissionedClassification;
import com.georgiev.payroll.request.PayrollRequest;
import java.math.BigDecimal;

public class AddCommissionedEmployee extends AddEmployeeTransaction {

  private final BigDecimal salary;
  private final BigDecimal commissionRate;

  public AddCommissionedEmployee(int employeeId,
                                 String name,
                                 String address,
                                 BigDecimal salary,
                                 BigDecimal commissionRate) {
    super(employeeId, name, address);
    this.salary = salary;
    this.commissionRate = commissionRate;
  }

  public AddCommissionedEmployee(PayrollRequest request) {
    super(request.getId(), request.getName(), request.getAddress());
    this.salary = request.getSalary();
    this.commissionRate = request.getCommissionRate();
  }

  @Override
  protected PaymentClassification getClassification() {
    return new CommissionedClassification(salary, commissionRate);
  }

  @Override
  protected PaymentSchedule getSchedule() {
    return new BiweeklySchedule();
  }

}
