package com.georgiev.web.controller;

import java.math.BigDecimal;

public class Employee {
  private Integer id;
  private String name;
  private String address;
  private String type;
  private BigDecimal salary;
  private BigDecimal commissionRate;
  private BigDecimal basePay;
  private BigDecimal hourlyRate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public BigDecimal getCommissionRate() {
    return commissionRate;
  }

  public void setCommissionRate(BigDecimal commissionRate) {
    this.commissionRate = commissionRate;
  }

  public BigDecimal getBasePay() {
    return basePay;
  }

  public void setBasePay(BigDecimal basePay) {
    this.basePay = basePay;
  }

  public BigDecimal getHourlyRate() {
    return hourlyRate;
  }

  public void setHourlyRate(BigDecimal hourlyRate) {
    this.hourlyRate = hourlyRate;
  }
}