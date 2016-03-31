package com.georgiev.web.controller;

import com.georgiev.data.objects.EmployeeForm;
import com.georgiev.data.objects.EmployeeFormResolverService;
import com.georgiev.model.PayrollModel;
import com.georgiev.services.ChangeEmplyeeService;
import com.georgiev.services.DataBaseProviderService;
import com.georgiev.services.FindEmplyeeService;
import com.georgiev.util.Constants;
import com.georgiev.utils.ConvertEmployeeUtils;
import com.georgiev.utils.ConvertResponseUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChangeEmployeeController {

  PayrollModel payrollModel = new PayrollModel();

  @Autowired
  private FindEmplyeeService findEmplyeeService;

  @Autowired
  private ChangeEmplyeeService changeEmplyeeService;

  @Autowired
  private DataBaseProviderService dataBaseProviderService;

  @Autowired
  private EmployeeFormResolverService employeeFormResolverService;

  @RequestMapping(value = { "/edit-user-{id}" }, method = RequestMethod.GET)
  public ModelAndView editUser(@PathVariable Integer id, ModelMap model) {
    Map<String, Object> data = createData(id);
    Map<String, Object> employeeMap = findEmplyeeService.findEmployee(data,
                                                                      dataBaseProviderService.getDataBaseImpl());
    List<EmployeeForm> emplyees = ConvertResponseUtils.dataToEmployeeForm(employeeMap);
    initModels(model, emplyees.get(0));

    return new ModelAndView("employee", "command", emplyees.get(0));
  }

  private Map<String, Object> createData(Integer id) {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), id);
    return data;
  }

  @RequestMapping(value = "/changeEmployee", method = RequestMethod.POST)
  public ModelAndView updateEmployee(@ModelAttribute("employee") EmployeeForm form, ModelMap model) {
    EmployeeForm prevEmp = payrollModel.getEmployeeForm();

    if (isNameChanged(form, prevEmp)) {
      changeEmplyeeService.changeEmplyeeName(ConvertEmployeeUtils.convertEmployeeDoToData(form),
                                             dataBaseProviderService.getDataBaseImpl());
    }

    if (isTypeChanged(form, prevEmp)) {
      return changeEmployeeType(model, form);
    }
    else {
      changeEmployeeType(form);
      return new ModelAndView("result");
    }

  }

  private ModelAndView changeEmployeeType(ModelMap model, EmployeeForm form) {
    initModels(model, form);
    return new ModelAndView("employee", "command", form);
  }

  private void changeEmployeeType(EmployeeForm form) {
    Map<String, Object> data = employeeFormResolverService.resolveForm(form).getDataAsMap();
    if (form.getType().equals("Salaried")) {
      changeEmplyeeService.changeEmplyeeToSalaried(data, dataBaseProviderService.getDataBaseImpl());
    }
    else if (form.getType().equals("Hourly")) {
      changeEmplyeeService.changeEmplyeeToHourly(data, dataBaseProviderService.getDataBaseImpl());
    }
    else if (form.getType().equals("Commissioned")) {
      changeEmplyeeService.changeEmplyeeToCommissioned(data, dataBaseProviderService.getDataBaseImpl());
    }
  }

  private boolean isTypeChanged(EmployeeForm form, EmployeeForm prevEmp) {
    return !form.getType().equals(prevEmp.getType());
  }

  private boolean isNameChanged(EmployeeForm form, EmployeeForm prevEmp) {
    return !form.getName().equals(prevEmp.getName());
  }

  private void initModels(ModelMap model, EmployeeForm form) {
    model.addAttribute("action", "/payroll-spring-mvc/changeEmployee");
    model.addAttribute("employee", form);
    payrollModel.setEmployeeForm(form);
  }

}