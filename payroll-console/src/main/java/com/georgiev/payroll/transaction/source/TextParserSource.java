package com.georgiev.payroll.transaction.source;

import com.georgiev.payroll.request.PayrollRequest;
import com.georgiev.payroll.transaction.Transaction;
import com.georgiev.payroll.transaction.TransactionFactory;
import com.georgiev.request.PayRollRequestImpl;

import java.math.BigDecimal;

public class TextParserTransactionSource implements TransactionSource {

  private final TransactionFactory factory;
  private final String line;

  public TextParserTransactionSource(TransactionFactory factory, String line) {
    this.factory = factory;
    this.line = line;
  }

  @Override
  public Transaction getTransaction() {
    return parseLine();
  }

  private Transaction parseLine() {
    String[] parts = line.split(",");
    if (parts[4].equals("H")) {
      return factory.makeAddHourlyTransaction(integer(parts[1]), parts[2], parts[3], decimal(parts[5]));
    }
    else {
      return factory.makeAddCommissionedTransaction(createPayrollRequest(parts));
    }
  }

  private PayrollRequest createPayrollRequest(String[] parts) {
    PayRollRequestImpl pr = new PayRollRequestImpl();
    pr.setEmployeeId(integer(parts[1].trim()));
    pr.setName(parts[2].trim());
    pr.setAddres(parts[3].trim());
    pr.setSalary(decimal(parts[5].trim()));
    pr.setCommissionRate(decimal(parts[6].trim()));
    return pr;
  }

  private Integer integer(String value) {
    return Integer.valueOf(value);
  }

  private BigDecimal decimal(String value) {
    return new BigDecimal(value);
  }

}
