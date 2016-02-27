package com.georgiev.web.controller;

public class Employee {
  private int id;
  private String name;
  private String address;
  private String type;

  // ransaction Format: Usecase, ID, Name, Address, EmpType, Sallary, Rate >
  // ");

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

}