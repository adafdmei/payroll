package com.georgiev.web.controller;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.georgiev.builder.RequestBuilder;
import com.georgiev.builder.RequestBuilderImpl;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.usecases.UseCase;
import com.georgiev.usecases.factory.UseCaseFactory;
import com.georgiev.usecases.factory.impl.UseCaseFactoryImpl;
import com.georgiev.util.Constants;

@Controller
public class EmployeeController {

  RequestBuilder requestBuilder = new RequestBuilderImpl();
  UseCaseFactory factory = new UseCaseFactoryImpl();

  @PostConstruct
  private void initDB() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
  }

  @RequestMapping(value = "/employee", method = RequestMethod.GET)
  public ModelAndView employee() {

    return new ModelAndView("employee", "command", new Employee());
  }

  @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
  public String addStudent(@ModelAttribute("SpringWeb") Employee employee, ModelMap model) {
    model.addAttribute("id", employee.getId());
    model.addAttribute("name", employee.getName());
    model.addAttribute("address", employee.getAddress());
    model.addAttribute("type", employee.getType());

    Map<String, Object> dataArgs = new HashMap<String, Object>();

    dataArgs.put(Constants.EMPLOYEE_ID.name(), employee.getId());
    dataArgs.put(Constants.NAME.name(), employee.getName());
    dataArgs.put(Constants.ADDRESS.name(), employee.getAddress());
    dataArgs.put(Constants.BASE_PAY.name(), BigDecimal.ONE);
    dataArgs.put(Constants.COMMISSION_RATE.name(), BigDecimal.ONE);

    Request request = requestBuilder.buildCommissionedEmployeeRequest(dataArgs);

    UseCase makeAddCommisionedEmployee = factory.makeAddCommisionedEmployee();

    try {
      makeAddCommisionedEmployee.execute(request);
      model.addAttribute("result", "OK");
    }
    catch (Exception e) {
      model.addAttribute("result", "Fail");
    }
    return "result";
  }
}