package com.georgiev.payroll.transaction.source;

import com.georgiev.payroll.transaction.Transaction;

public interface TransactionSource {

  Transaction getTransaction();

}
