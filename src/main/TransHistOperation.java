package main;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import adt.*;
import entity.*;

public class TransHistOperation {
    public static Scanner scan = new Scanner(System.in);
    public static StackInterface<TransHist> tran_history = new LinkedStack<>();

    public void print_Tran_History() {
        DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss a");

        System.out.println("\n+----------------------------------------------------------------+");
        System.out.println("|                      TRANSACTION HISTORY                       |");
        System.out.println("+----------------------------------------------------------------+");
         System.out.printf("| %-7s  |  %-10s  |  %-11s  | %-8s | %-9s |\n", "TRAN ID", "DATE", "TIME", "ORDER ID", "TOTAL(RM)");
        System.out.println("+----------|--------------|---------------|----------|-----------+");
        Iterator<TransHist> it = tran_history.getIterator();
        while(it.hasNext()) {
            TransHist th = it.next();
            System.out.printf("|    %-5s |  %-10s  |  %-11s  |   %-5s  |   %-5.2f   |\n", th.getTranID(),
                    th.getTranTime().format(formatter_date),
                    th.getTranTime().format(formatter_time),
                    th.getTranDetail().getOrderID(),
                    th.getTranDetail().getOrderTotalPrice()
            );
        }
        System.out.println("+----------------------------------------------------------------+");
    }

    public static void push(TransHist t){
        tran_history.push(t);
    }
}
