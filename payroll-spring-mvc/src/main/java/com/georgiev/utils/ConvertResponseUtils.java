package com.georgiev.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.georgiev.data.objects.EmployeeForm;
import com.georgiev.payroll.domain.Employee;
import com.georgiev.payroll.impl.Commissioned;
import com.georgiev.payroll.impl.Hourly;
import com.georgiev.payroll.impl.Salaried;

public class ConvertResponseUtils {

  public static List<EmployeeForm> dataToEmployeeForm(Map<String, Object> employeeMap) {
    List<EmployeeForm> employeeList = new ArrayList<EmployeeForm>();

    for (String empId : employeeMap.keySet()) {
      Employee employee = (Employee) employeeMap.get(empId);
      EmployeeForm emp = new EmployeeForm("type");
      emp.setId(employee.getEmployeeId());
      emp.setName(employee.getName());
      emp.setAddress(employee.getAddress());

      if (employee.getPayType() instanceof Salaried) {
        emp.setType("Salaried");
        BigDecimal salary = ((Salaried) employee.getPayType()).getSalary();
        emp.setSalary(salary);
      }
      else if (employee.getPayType() instanceof Commissioned) {
        emp.setType("Commissioned");
        BigDecimal commissionRate = ((Commissioned) employee.getPayType()).getCommissionRate();
        BigDecimal basePay = ((Commissioned) employee.getPayType()).getBasePay();
        emp.setBasePay(basePay);
        emp.setCommissionRate(commissionRate);
      }
      else if (employee.getPayType() instanceof Hourly) {
        emp.setType("Hourly");
        BigDecimal hourlyRate = ((Hourly) employee.getPayType()).getHourlyRate();
        emp.setHourlyRate(hourlyRate);
      }
      employeeList.add(emp);
    }
    return employeeList;
  }

}