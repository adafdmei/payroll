package com.georgiev.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.georgiev.data.objects.EmployeeForm;
import com.georgiev.services.FindEmplyeeService;
import com.georgiev.util.Constants;
import com.georgiev.utils.ConvertResponseUtils;

@Controller
public class ShowEmployeeController {

  @Autowired
  private FindEmplyeeService findEmplyeeService;

  @RequestMapping(value = "/findAllEmployees", method = RequestMethod.GET)
  public ModelAndView findAllEmployees(ModelMap model) {
    Map<String, Object> employeesMap = findEmplyeeService.findAllEmployees();
    model.addAttribute("employees", ConvertResponseUtils.dataToEmployeeForm(employeesMap));

    return new ModelAndView("tables");
  }

  @RequestMapping(value = "/findEmployee", method = RequestMethod.GET)
  public ModelAndView createFindEmployeeForm() {
    return new ModelAndView("find_employee", "command", new EmployeeForm());
  }

  @RequestMapping(value = "/findEmployee", method = RequestMethod.POST)
  public ModelAndView showEmployee(EmployeeForm employee, ModelMap model) {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), employee.getId());
    Map<String, Object> employeesMap = findEmplyeeService.findEmployee(data);
    model.addAttribute("employees", ConvertResponseUtils.dataToEmployeeForm(employeesMap));

    return new ModelAndView("tables");
  }

}