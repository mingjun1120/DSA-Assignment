package main;

import adt.*;
import entity.Report;
import java.util.Scanner;

public class ReportOperation {
    ReportListInterface<Report> reportList = new SortedLinkedList<>();
    public static Scanner scan = new Scanner(System.in);

    public void display() {
        //read_data_from_File();
        System.out.print("\n");
        System.out.println("\n+-----------------------------------------------------------------------------------------+");
        System.out.println("|                                    NOODLES UNLIMITED                                    |");
        System.out.println("|                          TARUC KUALA LUMPUR, JLN GENTING KLANG                          |");
        System.out.println("|                           Lot 5.103.00 - 5.105.00 & P5.10.00,                           |");
        System.out.println("|                               53300 Kuala Lumpur, Malaysia                              |");
        System.out.println("|                                                                                         |");
        System.out.println("|                                          REPORT                                         |");
        System.out.println("|                                       -----------                                       |");
        //System.out.printf("| %-4s    %-4s    %-18s    %-8s    |\n", "Dish", "ID", "Name", "Price(RM)");
        //System.out.println("|----------------------------------------------------|");
        //for (int position = 1; position <= menuList.getLength(); position++) {
            //System.out.printf("| %-4d    %-4s    %-18s    %-9.2f    |\n", position, menuList.getEntry(position).getId(), menuList.getEntry(position).getName(), menuList.getEntry(position).getPrice());
        //}
        //System.out.println("+----------------------------------------------------+");
    }

    public static void main(String[] args) {

    }
}
