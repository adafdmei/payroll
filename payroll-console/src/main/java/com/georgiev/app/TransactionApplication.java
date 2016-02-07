package com.georgiev.app;

import com.georgiev.payroll.transaction.Transaction;
import com.georgiev.payroll.transaction.source.TransactionSource;

public class TransactionApplication extends Application {

  private final TransactionSource source;

  public TransactionApplication(TransactionSource source) {
    this.source = source;
  }

  @Override
  public void run() {
    Transaction trans;
    while ((trans = source.getTransaction()) != null) {
      trans.execute();
    }
  }

}
