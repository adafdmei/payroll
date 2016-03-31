package com.georgiev.payroll.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Commissioned", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class CommissionedEmployeeEntity extends EmployeeEntity {

  @Column(name = "COMMISSION_RATE", length = 20, nullable = true)
  private BigDecimal commissionRate;

  @Column(name = "BASE_PAY", length = 20, nullable = true)
  private BigDecimal basePay;

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

}
