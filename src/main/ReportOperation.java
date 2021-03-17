package main;
import static main.mainClass.doSelection;
import adt.*;
import entity.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class ReportOperation {
    ReportListInterface<Report> reportList = new SortedLinkedList<>();
    QueueInterface<Order> orderedList = new LinkedQueue<>();
    public static Scanner scan = new Scanner(System.in);

    DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss a");

    public void display_Daily_Sales_Report() {
        readOrderedListTextFile();
        System.out.print("\n");
        System.out.println("\n+-----------------------------------------------------------------------------------------+");
        System.out.println("|                                    NOODLES UNLIMITED                                    |");
        System.out.println("|                          TARUC KUALA LUMPUR, JLN GENTING KLANG                          |");
        System.out.println("|                           Lot 5.103.00 - 5.105.00 & P5.10.00,                           |");
        System.out.println("|                               53300 Kuala Lumpur, Malaysia                              |");
        System.out.println("|                                                                                         |");

        // Report Id name date
        //System.out.println("|                                    Daily Sales Report                                   |");
        //System.out.println("|                                        -----------                                      |");
        //System.out.printf("| %-10s%-4s%-10s%-9s\n", "Date", "ID", "Name", "Price(RM)");

    }

    public void display_Weekly_Sales_Report() {
        //readOrderedListTextFile();
        System.out.print("\n");
        System.out.println("\n+-----------------------------------------------------------------------------------------+");
        System.out.println("|                                    NOODLES UNLIMITED                                    |");
        System.out.println("|                          TARUC KUALA LUMPUR, JLN GENTING KLANG                          |");
        System.out.println("|                           Lot 5.103.00 - 5.105.00 & P5.10.00,                           |");
        System.out.println("|                               53300 Kuala Lumpur, Malaysia                              |");
        System.out.println("|                                                                                         |");
        System.out.println("|                                          REPORT                                         |");
        System.out.println("|                                       -----------                                       |");
    }

    public void display_Dish_Rankings_Report() {
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
    }

    private void readOrderedListTextFile() {
        try {
            FileInputStream fis = new FileInputStream("src/orderedList.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            orderedList = (LinkedQueue<Order>)ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
    }

}
