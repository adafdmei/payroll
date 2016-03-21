package com.georgiev.app;

import static com.georgiev.payroll.db.PayrollDatabase.GlobalInstance.GpayrollDatabase;

import java.io.IOException;
import java.util.Scanner;

import com.georgiev.builder.RequestBuilder;
import com.georgiev.builder.impl.RequestBuilderImpl;
import com.georgiev.payroll.db.impl.InMemoryPayrollDatabase;
import com.georgiev.payroll.request.Request;
import com.georgiev.payroll.transaction.source.TextParserSource;

public class PayrollApplication {

  public static void main(String[] args) throws IOException {
    initDB();
    while (true) {
      printPrompt();
      String line = new Scanner(System.in, "UTF-8").nextLine();
      if (line.equals("q")) {
        break;
      }
      else {
        processInput(line);
      }
    }
  }

  private static void initDB() {
    GpayrollDatabase = new InMemoryPayrollDatabase();
  }

  private static void printPrompt() {
    System.out.println("Press q for exit!");
    System.out.println("Transaction Format: Usecase, ID, Name, Address, EmpType, Sallary, Rate   > ");
    System.out.print("Enter a transaction > ");
  }

  private static void processInput(String line) {
    try {
      RequestBuilder requestBuilder = new RequestBuilderImpl();

      TextParserSource source = new TextParserSource(line);
      Request request = requestBuilder.buildCommissionedEmployeeRequest(source.getDataArgs());
      TransactionApplication app = new TransactionApplication(request);
      app.run();
    }
    catch (Exception e) {
      System.out.println("Please use a correct syntax");
    }
  }
}
