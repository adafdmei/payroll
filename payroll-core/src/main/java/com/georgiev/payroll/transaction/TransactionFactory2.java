package com.georgiev.payroll.transaction;

import com.georgiev.payroll.request.PayrollRequest;

public interface TransactionFactory2 {

  Transaction make(String transactionType, PayrollRequest request);

}