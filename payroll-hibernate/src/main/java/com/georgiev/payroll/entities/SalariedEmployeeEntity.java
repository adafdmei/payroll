package com.georgiev.payroll.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Salaried", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class SalariedEmployeeEntity extends EmployeeEntity {

  @Column(name = "SALARY", length = 20, nullable = true)
  private BigDecimal salary;

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }
}
