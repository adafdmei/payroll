package com.payroll;

import java.util.Calendar;
import java.util.Date;

public class TestUtils {
  public static Date date(int month, int day, int year) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.set(year, month - 1, day);
    return c.getTime();
  }
}
