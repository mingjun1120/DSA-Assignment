package main;
import adt.*;
import adt.LinkedList;
import entity.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class ReportOperation {
    SortedLinkedListInterface<Report> reportList = new SortedLinkedList<>();
    ListInterface<OrderDish> orderList = new LinkedList<>();
    QueueInterface<Order> orderedList = new LinkedQueue<>();

    public static Scanner scan = new Scanner(System.in);

    public void display_Daily_Sales_Report() {
        DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss a");

        readOrderedListTextFile();
        display_Report_Header();

        Iterator<Order> it = orderedList.getIterator();
        while(it.hasNext()) {
            Order order = it.next();
            System.out.printf("|  %-5s |   %-10s   |  %-11s  |",
                    order.getOrderID(),
                    order.getOrderTime().format(formatter_date),
                    order.getOrderTime().format(formatter_time)
            );
            int count = 0;
            for (OrderDish od : order.getCusOrder()){
                if(count > 0)
                    System.out.printf("%43s", " ");
                System.out.printf("  %-13s    |  %9.2f      |  %5d     | %10.2f|\n",
                        od.getChosenDish().getName(),
                        od.getChosenDish().getPrice(),
                        od.getQty(),
                        od.getChosenDish().getPrice() * od.getQty()
                );
                count++;
            }
        }
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

    private void display_Report_Header() {
        System.out.print("\n");
        System.out.println("\n+--------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                            NOODLES UNLIMITED                                           |");
        System.out.println("|                                  TARUC KUALA LUMPUR, JLN GENTING KLANG                                 |");
        System.out.println("|                                   Lot 5.103.00 - 5.105.00 & P5.10.00,                                  |");
        System.out.println("|                                       53300 Kuala Lumpur, Malaysia                                     |");
        System.out.println("|                                                                                                        |");
        System.out.println("|                                            Daily Sales Report                                          |");
        System.out.println("|                                                -----------                                             |");
        System.out.printf("|%-13s  %-14s  %-15s  %-15s %-17s %-12s %s|\n", "Order ID", "Date", "Time", "Dish Name", "Price Each(RM)", "Quantity", "Price(RM)");
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

    public static void main(String[] args) {
        SortedLinkedListInterface<Report> reportList = new SortedLinkedList<>();
        ListInterface<OrderDish> orderList = new LinkedList<>();
        QueueInterface<Order> orderedList = new LinkedQueue<>();

        ReportOperation reportOp = new ReportOperation();

        reportOp.display_Daily_Sales_Report();
    }

}
