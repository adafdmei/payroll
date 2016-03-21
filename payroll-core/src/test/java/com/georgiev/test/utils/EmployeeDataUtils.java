package com.georgiev.test.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.georgiev.util.Constants;

public class EmployeeDataUtils {

  public static int getId(Map<String, Object> data) {
    return (int) data.get(Constants.ID.name());
  }

  public static BigDecimal getServiceCharge(Map<String, Object> data) {
    return (BigDecimal) data.get(Constants.SERVICE_CHARGE.name());
  }

  public static BigDecimal getSoldAmount(Map<String, Object> data) {
    return (BigDecimal) data.get(Constants.SOLD_AMOUNT.name());
  }

  public static Date getDate(Map<String, Object> data) {
    return (Date) data.get(Constants.DATE.name());
  }

  public static Date getPayDate(Map<String, Object> data) {
    return (Date) data.get(Constants.PAY_DATE.name());
  }

  public static Object getName(Map<String, Object> data) {
    return data.get(Constants.NAME.name());
  }

  public static BigDecimal getHourlyRate(Map<String, Object> data) {
    return (BigDecimal) data.get(Constants.HOURLY_RATE.name());
  }

  public static int getMemberId(Map<String, Object> data) {
    return (int) data.get(Constants.MEMBER_ID.name());
  }

}
