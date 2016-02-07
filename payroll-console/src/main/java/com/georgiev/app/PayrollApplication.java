package com.georgiev.app;

import com.georgiev.payroll.transaction.TransactionFactory;
import com.georgiev.payroll.transaction.source.TextParserTransactionSource;
import com.georgiev.transaction.TransactionFactoryImpl;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PayrollApplication {

  public static void main(String[] args) throws IOException {
    InputStreamReader is = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(is);
    String text = "";

    TransactionFactory transFactory = new TransactionFactoryImpl();

    while (!text.equals("q")) {
      try {
        System.out.println("Press q for exit!");
        System.out.println("Transaction Format: Usecase, ID, Name, Address, EmpType, Sallary, Rate   > ");
        System.out.print("Enter a transaction > ");
        text = br.readLine();
        System.out.print("You entered : ");
        System.out.println(text);

        InputStream inputStream = new ByteArrayInputStream(text.getBytes("UTF-8"));
        TextParserTransactionSource source = new TextParserTransactionSource(transFactory, inputStream);
        TransactionApplication app = new TransactionApplication(source);
        app.run();
      }
      catch (Exception e) {
        System.out.println("Please use a correct syntax");
      }

    }
    br.close();
    is.close();

  }
}
