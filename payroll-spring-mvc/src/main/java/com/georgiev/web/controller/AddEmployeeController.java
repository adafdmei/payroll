package com.georgiev.web.controller;

import com.georgiev.data.objects.EmployeeData;
import com.georgiev.data.objects.EmployeeForm;
import com.georgiev.data.objects.EmployeeFormResolverService;
import com.georgiev.model.PayrollModel;
import com.georgiev.services.AddEmplyeeService;
import com.georgiev.services.DataBaseProviderService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddEmployeeController {

  PayrollModel payrollModel = new PayrollModel();

  @Autowired
  private AddEmplyeeService addEmplyeeService;

  @Autowired
  private DataBaseProviderService dataBaseProviderService;

  @Autowired
  private EmployeeFormResolverService employeeFormResolverService;

  @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
  public ModelAndView addEmployee(ModelMap model) {
    EmployeeForm employee = (EmployeeForm) model.get("employee");
    if (employee == null) {
      employee = new EmployeeForm("Salaried");
    }
    initModels(model, employee);

    return new ModelAndView("employee", "command", employee);
  }

  private void initModels(ModelMap model, EmployeeForm employee) {
    payrollModel.setEmployeeForm(employee);
    model.addAttribute("employee", employee);
    model.addAttribute("action", "/payroll-spring-mvc/addEmployee");
  }

  @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
  public ModelAndView addEmployeeIntoDatabase(@ModelAttribute("employee") EmployeeForm form, ModelMap model) {
    if (isAnotherEmployeeTypeSelected(form)) {
      return addEmployee(model);
    }
    else {
      addEmployee(form);
      model.addAttribute("result", "OK");
      return new ModelAndView("result");
    }
  }

  private boolean isAnotherEmployeeTypeSelected(EmployeeForm form) {
    return !form.getType().equals(payrollModel.getEmployeeForm().getType());
  }

  private void addEmployee(EmployeeForm form) {
    EmployeeData resolveForm = employeeFormResolverService.resolveForm(form);

    System.out.println(resolveForm);
    Map<String, Object> data = employeeFormResolverService.resolveForm(form).getDataAsMap();

    if (isCommissionnedEmployee(form)) {
      addEmplyeeService.addCommisionedEmployee(data, dataBaseProviderService.getDataBaseImpl());
    }
    else if (isSalariedEmployee(form)) {
      addEmplyeeService.addSalariedEmpolyee(data, dataBaseProviderService.getDataBaseImpl());
    }
    else if (isHourlyEmployee(form)) {
      addEmplyeeService.addHourlyEmployee(data, dataBaseProviderService.getDataBaseImpl());
    }
  }

  private boolean isSalariedEmployee(EmployeeForm form) {
    return form.getType().equals("Salaried");
  }

  private boolean isCommissionnedEmployee(EmployeeForm form) {
    return form.getType().equals("Commissioned");
  }

  private boolean isHourlyEmployee(EmployeeForm form) {
    return form.getType().equals("Hourly");
  }

}