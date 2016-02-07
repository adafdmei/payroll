package com.georgiev.app;

import com.georgiev.payroll.transaction.source.TextParserTransactionSource;
import com.georgiev.transaction.TransactionFactoryImpl;

import java.io.IOException;
import java.util.Scanner;

public class PayrollApplication {

  public static void main(String[] args) throws IOException {
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

  private static void printPrompt() {
    System.out.println("Press q for exit!");
    System.out.println("Transaction Format: Usecase, ID, Name, Address, EmpType, Sallary, Rate   > ");
    System.out.print("Enter a transaction > ");
  }

  private static void processInput(String line) {
    try {
      TransactionFactoryImpl factory = new TransactionFactoryImpl();
      TextParserTransactionSource source = new TextParserTransactionSource(factory, line);
      TransactionApplication app = new TransactionApplication(source);
      app.run();
    }
    catch (Exception e) {
      System.out.println("Please use a correct syntax");
    }
  }
}
