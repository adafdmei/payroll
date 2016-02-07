package com.georgiev.web.controller;

import java.math.BigDecimal;

import com.georgiev.payroll.request.PayrollRequest;

public class Employee  implements PayrollRequest{
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
	
	@Override
	public BigDecimal getSalary() {
		// TODO Auto-generated method stub
		return BigDecimal.ONE;
	}
	@Override
	public BigDecimal getCommissionRate() {
		// TODO Auto-generated method stub
		return BigDecimal.ONE;
	}

	
	
}