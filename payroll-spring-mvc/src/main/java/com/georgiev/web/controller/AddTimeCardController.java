package com.georgiev.web.controller;

import com.georgiev.data.objects.EmployeeForm;
import com.georgiev.data.objects.EmployeeFormResolverService;
import com.georgiev.model.PayrollModel;
import com.georgiev.payroll.forms.TimeCardForm;
import com.georgiev.services.AddTimeCardService;
import com.georgiev.services.ChangeEmplyeeService;
import com.georgiev.services.DataBaseProviderService;
import com.georgiev.services.FindEmplyeeService;
import com.georgiev.util.Constants;
import com.georgiev.utils.ConvertResponseUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AddTimeCardController {

  PayrollModel payrollModel = new PayrollModel();

  @Autowired
  AddTimeCardService addTimeCardService;

  @Autowired
  private FindEmplyeeService findEmplyeeService;

  @Autowired
  private ChangeEmplyeeService changeEmplyeeService;

  @Autowired
  private DataBaseProviderService dataBaseProviderService;

  @Autowired
  private EmployeeFormResolverService employeeFormResolverService;

  @RequestMapping(value = { "/add-timecard-for-user-{id}" }, method = RequestMethod.GET)
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

  @RequestMapping(value = "/addTimeCard", method = RequestMethod.GET)
  public ModelAndView addTimeCard(ModelMap model) {
    model.addAttribute("action", "/payroll-spring-mvc/addTimeCard");
    return new ModelAndView("timecard", "command", new TimeCardForm());

  }

  @RequestMapping(value = "/addTimeCard", method = RequestMethod.POST)
  public ModelAndView addTimeCardPost(@ModelAttribute("timecard") TimeCardForm form, ModelMap model) {

    Map<String, Object> data = new HashMap<String, Object>();
    data.put(Constants.ID.name(), form.getEmployeeId());
    data.put(Constants.DATE.name(), convertDate(form.getDate()));
    data.put(Constants.HOURS.name(), form.getHours());

    addTimeCardService.addTimeCard(data, dataBaseProviderService.getDataBaseImpl());
    return addTimeCard(model);

  }

  private void initModels(ModelMap model, EmployeeForm form) {
    model.addAttribute("action", "/payroll-spring-mvc/changeEmployee");
    model.addAttribute("employee", form);
    payrollModel.setEmployeeForm(form);
  }

  public Date convertDate(String dateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(true);
    Date date = new Date();
    try {
      date = sdf.parse(dateStr);
    }
    catch (Exception e) {
      // TODO: handle exception
    }

    return date;
  }

}