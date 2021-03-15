package main;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import adt.*;
import entity.*;

public class TransHistOperation {
    public static Scanner scan = new Scanner(System.in);
    StackInterface<TransHist> tran_history = new LinkedStack<>();

    public void print_Tran_History() {
        DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss a");

        System.out.println("+---------------------------------------------------+");
        System.out.println("|                TRANSACTION HISTORY                |");
        System.out.println("+---------------------------------------------------+");

        Iterator<TransHist> it = tran_history.getIterator();
        while(it.hasNext()) {
            System.out.printf("|  %-5s  |  %-10s  |  %-11s  |  %-5s  |  -%5.2f  |\n", it.next().getTranID(),
                                                     it.next().getTranTime().format(formatter_date),
                                                     it.next().getTranTime().format(formatter_time),
                                                     it.next().getTranDetail().getOrderID(),
                                                     it.next().getTranDetail().getOrderTotalPrice()
            );
        }
        System.out.println(tran_history.isEmpty());
    }
}
