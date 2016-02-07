package com.georgiev.web.controller;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.factory.impl.TransactionFactoryImpl2;
import com.georgiev.payroll.transaction.Transaction;
import com.georgiev.payroll.transaction.TransactionFactory2;

@Controller
public class EmployeeController {
	@PostConstruct
	private void initDB(){
		GpayrollDatabase = new InMemoryPayrollDatabase();
	}
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ModelAndView employee() {
		
		return new ModelAndView("employee", "command", new Employee());
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb") Employee employee,
			ModelMap model) {
		model.addAttribute("id", employee.getId());
		model.addAttribute("name", employee.getName());
		model.addAttribute("address", employee.getAddress());
		model.addAttribute("type", employee.getType());

		TransactionFactory2 transFactory = new TransactionFactoryImpl2();
		Transaction transaction = transFactory.make("addC", employee);

		try {
			transaction.execute();
			model.addAttribute("result", "OK");
		} catch (Exception e) {
			model.addAttribute("result", "Fail");
		}
		return "result";
	}
}