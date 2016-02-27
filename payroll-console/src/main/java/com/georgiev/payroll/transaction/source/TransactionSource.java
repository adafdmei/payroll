package com.georgiev.payroll.transaction.source;

import java.util.Map;

public interface TransactionSource {

  public Map<String, Object> getDataArgs();

}
