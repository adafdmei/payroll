package com.georgiev.payroll.factory.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.georgiev.payroll.request.PayrollRequest;
import com.georgiev.payroll.transaction.Transaction;
import com.georgiev.payroll.transaction.TransactionFactory;
import com.georgiev.payroll.transaction.TransactionFactory2;
import com.georgiev.payroll.transaction.impl.AddCommissionedEmployee;
import com.georgiev.payroll.transaction.impl.AddHourlyEmployee;
import com.georgiev.payroll.transaction.impl.AddSalariedEmployee;
import com.georgiev.payroll.transaction.impl.ChangeCommissionedTransaction;
import com.georgiev.payroll.transaction.impl.ChangeHourlyTransaction;
import com.georgiev.payroll.transaction.impl.ChangeMemberTransaction;
import com.georgiev.payroll.transaction.impl.ChangeNameTransaction;
import com.georgiev.payroll.transaction.impl.ChangeSalariedTransaction;
import com.georgiev.payroll.transaction.impl.ChangeUnaffiliatedTransaction;
import com.georgiev.payroll.transaction.impl.DeleteEmployeeTransaction;
import com.georgiev.payroll.transaction.impl.PaydayTransaction;
import com.georgiev.payroll.transaction.impl.SalesReceiptTransaction;
import com.georgiev.payroll.transaction.impl.ServiceChargeTransaction;
import com.georgiev.payroll.transaction.impl.TimeCardTransaction;

public class TransactionFactoryImpl2 implements TransactionFactory2 {

	@Override
	public Transaction make(String transactionType, PayrollRequest request) {
		
		if (transactionType.equals("addC")) {
			return new AddCommissionedEmployee(request);
		}
		
		return null;
	}


	

}
