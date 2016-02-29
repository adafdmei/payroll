package com.georgiev.web.controller;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

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

@Controller
public class EmployeeController {

  RequestBuilder requestBuilder = new RequestBuilderImpl();
  UseCaseFactory factory = new UseCaseFactoryImpl();

  @PostConstruct
  private void initDB() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
  }

  @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
  public ModelAndView addEmployee() {
    return new ModelAndView("employee", "command", new Employee());
  }

  @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
  public String addEmployeeToIntoDatabase(@ModelAttribute("employee") Employee employee, ModelMap model) {
    model.addAttribute("emp", employee);

    Request request = requestBuilder.buildCommissionedEmployeeRequest(prepareDataForEmplyeeRequest(employee));
    UseCase makeAddCommisionedEmployee = factory.makeAddCommisionedEmployee();

    try {
      makeAddCommisionedEmployee.execute(request);
    }
    catch (Exception e) {
      model.addAttribute("result", "Fail");
    }
    model.addAttribute("result", "OK");
    return "result";
  }

  private Map<String, Object> prepareDataForEmplyeeRequest(Employee employee) {
    PrepareDataForPayrollRequest prepDataForReq = new PrepareDataForPayrollRequest(employee);
    String type = employee.getType();
    if (type.equals("salaried")) {
      return prepDataForReq.prepareDataForSalaried();
    }
    else if (type.equals("commissioned")) {
      return prepDataForReq.prepareDataForCommissioned();
    }
    else {
      return prepDataForReq.prepareDataForHourly();
    }
  }
}