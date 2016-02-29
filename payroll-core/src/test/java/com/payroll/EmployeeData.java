package com.payroll;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.georgiev.util.Constants;

public class EmployeeData {

  public static Map<String, Object> getStandardDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.NAME.name(), "Bob");
    data.put(Constants.ADDRESS.name(), "Home");
    data.put(Constants.SALARY.name(), BigDecimal.valueOf(1000.00));
    data.put(Constants.BASE_PAY.name(), BigDecimal.valueOf(2500.00));
    data.put(Constants.COMMISSION_RATE.name(), BigDecimal.valueOf(3.2));
    data.put(Constants.HOURLY_RATE.name(), BigDecimal.valueOf(15.24));
    data.put(Constants.DATE.name(), TestUtils.date(10, 31, 2001));
    data.put(Constants.HOURS.name(), BigDecimal.valueOf(8.0));
    data.put(Constants.SOLD_AMOUNT.name(), BigDecimal.valueOf(525.00));
    data.put(Constants.MEMBER_ID.name(), 86);

    return data;
  }

  public static Map<String, Object> getEmployeeIdData() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    return data;
  }

  public static Map<String, Object> getChangeNameDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.NAME.name(), "Bil");
    return data;
  }

  public static Map<String, Object> getPayDayDataForSalariedEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.PAY_DATE.name(), TestUtils.date(11, 30, 2001));
    return data;
  }

  public static Map<String, Object> getPayDayDataForHourlyEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.PAY_DATE.name(), TestUtils.date(11, 9, 2001));
    return data;
  }

  public static Map<String, Object> getPayDayWrongDataForHourlyEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.PAY_DATE.name(), TestUtils.date(11, 8, 2001));
    return data;
  }

  public static Map<String, Object> getPayDayDataForCommissionedEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.PAY_DATE.name(), TestUtils.date(11, 16, 2012));
    return data;
  }

  public static Map<String, Object> getPayDayWrongDataForSalariedEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.PAY_DATE.name(), TestUtils.date(11, 29, 2001));
    return data;
  }

  public static Map<String, Object> getChangeTypeHourlyDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.HOURLY_RATE.name(), BigDecimal.valueOf(27.52));
    return data;
  }

  public static Map<String, Object> getChangeTypeSalariedDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.SALARY.name(), BigDecimal.valueOf(1000.00));
    return data;
  }

  public static Map<String, Object> getChangeTypeComissionedDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.BASE_PAY.name(), BigDecimal.valueOf(2500.00));
    data.put(Constants.COMMISSION_RATE.name(), BigDecimal.valueOf(3.2));
    return data;
  }

  public static Map<String, Object> getChangeUnionMembershipToMemberForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.MEMBER_ID.name(), 86);
    data.put(Constants.WEEKLY_DUES.name(), BigDecimal.valueOf(99.42));
    data.put(Constants.ID.name(), 1);
    return data;
  }

  public static Map<String, Object> getTimeCardTwoHoursForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 9, 2001));
    data.put(Constants.HOURS.name(), BigDecimal.valueOf(2.0));

    return data;
  }

  public static Map<String, Object> getTimeCardEightHoursForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 9, 2001));
    data.put(Constants.HOURS.name(), BigDecimal.valueOf(8.0));

    return data;
  }

  public static Map<String, Object> getTimeCardFiveHoursForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 5, 2001));
    data.put(Constants.HOURS.name(), BigDecimal.valueOf(5.0));

    return data;
  }

  public static Map<String, Object> getTimeCardPreviousPeriodForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 1, 2001));
    data.put(Constants.HOURS.name(), BigDecimal.valueOf(5.0));

    return data;
  }

  public static Map<String, Object> getTimeCardPreviousNextForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 20, 2001));
    data.put(Constants.HOURS.name(), BigDecimal.valueOf(5.0));

    return data;
  }

  public static Map<String, Object> getOvertimeTimeCardForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 9, 2001));
    data.put(Constants.HOURS.name(), BigDecimal.valueOf(9));

    return data;
  }

  public static Map<String, Object> getFirstSalesRecieptForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 16, 2012));
    data.put(Constants.SOLD_AMOUNT.name(), BigDecimal.valueOf(500.00));
    return data;
  }

  public static Map<String, Object> getSecondSalesRecieptDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 10, 2012));
    data.put(Constants.SOLD_AMOUNT.name(), BigDecimal.valueOf(250.50));
    return data;
  }

  public static Map<String, Object> getPreviousPeriodSalesRecieptDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), 1);
    data.put(Constants.DATE.name(), TestUtils.date(11, 1, 2012));
    data.put(Constants.SOLD_AMOUNT.name(), BigDecimal.valueOf(250.50));
    return data;
  }

  public static Map<String, Object> getServieChargeDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.MEMBER_ID.name(), 86);
    data.put(Constants.DATE.name(), TestUtils.date(11, 01, 2001));
    data.put(Constants.SERVICE_CHARGE.name(), BigDecimal.valueOf(9.42));
    return data;
  }

  public static Map<String, Object> getServiceChargeDataForHourlyEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.MEMBER_ID.name(), 86);
    data.put(Constants.DATE.name(), TestUtils.date(11, 9, 2001));
    data.put(Constants.SERVICE_CHARGE.name(), BigDecimal.valueOf(19.42));
    return data;
  }

  public static Map<String, Object> getServiceChargeForPreviousPeriodForHourlyEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.MEMBER_ID.name(), 86);
    data.put(Constants.DATE.name(), TestUtils.date(11, 1, 2001));
    data.put(Constants.SERVICE_CHARGE.name(), BigDecimal.valueOf(100.00));
    return data;
  }

  public static Map<String, Object> getServiceChargeForNextPeriodMapForHourlyEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.MEMBER_ID.name(), 86);
    data.put(Constants.DATE.name(), TestUtils.date(11, 1, 2001));
    data.put(Constants.SERVICE_CHARGE.name(), BigDecimal.valueOf(200.00));
    return data;
  }

  public static Map<String, Object> getUnionMembershipDataForEmployee() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.MEMBER_ID.name(), 86);
    data.put(Constants.WEEKLY_DUES.name(), BigDecimal.valueOf(9.42));
    data.put(Constants.ID.name(), 1);
    return data;
  }

}
