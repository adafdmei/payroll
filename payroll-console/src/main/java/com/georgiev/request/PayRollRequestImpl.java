package com.georgiev.request;

import com.georgiev.payroll.request.PayrollRequest;
import java.math.BigDecimal;

public class PayRollRequestImpl implements PayrollRequest {

  private int employeeId;
  private String name;
  private String address;
  private BigDecimal salary;
  private BigDecimal commissionRate;

  @Override
  public int getId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getAddress() {
    return address;
  }

  public void setAddres(String address) {
    this.address = address;
  }

  @Override
  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  @Override
  public BigDecimal getCommissionRate() {
    return commissionRate;
  }

  public void setCommissionRate(BigDecimal commissionRate) {
    this.commissionRate = commissionRate;
  }

}
