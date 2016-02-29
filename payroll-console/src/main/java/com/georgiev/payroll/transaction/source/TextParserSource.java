package com.georgiev.payroll.transaction.source;

import com.georgiev.util.Constants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TextParserSource implements TransactionSource {

  private final String line;

  public TextParserSource(String line) {
    this.line = line;
  }

  @Override
  public Map<String, Object> getDataArgs() {
    return parseLine();
  }

  private Map<String, Object> parseLine() {
    String[] parts = line.split(",");
    Map<String, Object> dataArgs = new HashMap<>();
    dataArgs.put(Constants.ID.name(), integer(parts[1].trim()));
    dataArgs.put(Constants.NAME.name(), parts[2].trim());
    dataArgs.put(Constants.ADDRESS.name(), parts[3].trim());
    if (parts[4].trim().equals("C")) {
      dataArgs.put(Constants.BASE_PAY.name(), decimal(parts[5].trim()));
      dataArgs.put(Constants.COMMISSION_RATE.name(), decimal(parts[6].trim()));
    }
    return dataArgs;
  }

  private Integer integer(String value) {
    return Integer.valueOf(value);
  }

  private BigDecimal decimal(String value) {
    return new BigDecimal(value);
  }

}
