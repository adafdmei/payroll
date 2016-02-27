package com.georgiev.payroll.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Employee {

  private final Map<Date, Paycheck> paychecks = new HashMap<Date, Paycheck>();

  private final int employeeId;
  private String name;
  private String address;
  private PaySchedule paySchedule;
  private PayDisposition payDisposition;
  private UnionMembership unionMembership;
  private PayType payType;

  public Employee(int employeeId, String name, String address) {
    this.employeeId = employeeId;
    this.name = name;
    this.address = address;
  }

  public Map<Date, Paycheck> getPaychecks() {
    return paychecks;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public PayType getPayType() {
    return payType;
  }

  public void setPayType(PayType payType) {
    this.payType = payType;
  }

  public PaySchedule getPaySchedule() {
    return paySchedule;
  }

  public void setPaySchedule(PaySchedule paySchedule) {
    this.paySchedule = paySchedule;
  }

  public PayDisposition getPayDisposition() {
    return payDisposition;
  }

  public void setMethod(PayDisposition payDisposition) {
    this.payDisposition = payDisposition;
  }

  public void setUnionMembership(UnionMembership unionMembership) {
    this.unionMembership = unionMembership;
  }

  public UnionMembership getAffiliation() {
    return unionMembership;
  }

  public boolean isPayDay(Date payDate) {
    return paySchedule.isPayDay(payDate);
  }

  public Date getPayPeriodStartDate(Date payDate) {
    return paySchedule.getPayPeriodStartDate(payDate);
  }

  public void payDay(Paycheck pc) {
    BigDecimal grossPay = payType.calculatePay(pc);
    BigDecimal deductions = unionMembership.calculateDeductions(pc);
    BigDecimal netPay = grossPay.subtract(deductions);
    pc.setGrossPay(grossPay);
    pc.setDeductions(deductions);
    pc.setNetPay(netPay);
    paychecks.put(pc.getPayPeriodEndDate(), pc);
    payDisposition.pay(pc);
  }

}
