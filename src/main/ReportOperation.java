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

        System.out.print("\n");
        System.out.println("\n+-----------------------------------------------------------------------------------------+");
        System.out.println("|                                    NOODLES UNLIMITED                                    |");
        System.out.println("|                          TARUC KUALA LUMPUR, JLN GENTING KLANG                          |");
        System.out.println("|                           Lot 5.103.00 - 5.105.00 & P5.10.00,                           |");
        System.out.println("|                               53300 Kuala Lumpur, Malaysia                              |");
        System.out.println("|                                                                                         |");
        System.out.println("|                                    Daily Sales Report                                   |");
        System.out.println("|                                        -----------                                      |");
        System.out.printf("|%-13s  %-14s  %-15s %7s %s %s %s\n", "Order ID", "Date", "Time", "Dish Name", "Price Each", "Quantity", "Price(RM)");

        //orderID, Date, Time, Dish Name, Price Each, qty, total price
        //                     Dish Name, Price Each, qty, total price


        Iterator<Order> it = orderedList.getIterator();
        while(it.hasNext()) {
            Order order = it.next();
            System.out.printf("|  %-5s |   %-10s   |  %-11s  |   %-5.2f  |   %-20s   |\n",
                    order.getOrderID(),
                    order.getOrderTime().format(formatter_date),
                    order.getOrderTime().format(formatter_time),
                    order.getOrderTotalPrice(),
                    order.getCusOrder()
            );
        }
//        Order order = it1.next();
//        System.out.printf("|    %5s |  %-10s  |  %-11s  |   %-5.2f  |   %-20s   |\n",
//                order.getOrderID(),
//                order.getOrderTime().format(formatter_date),
//                order.getOrderTime().format(formatter_time),
//                order.getOrderTotalPrice(),
//                order.getCusOrder().length
//        );

        for (int position = 1; position <= orderList.getLength(); position++) {
            System.out.printf("| %-4d   %-18s    %-8d    %-18.2f    %-9.2f|\n",
                    position,
                    orderList.getEntry(position).getChosenDish().getName(),
                    orderList.getEntry(position).getQty(),
                    orderList.getEntry(position).getChosenDish().getPrice(),
                    orderList.getEntry(position).getQty() * orderList.getEntry(position).getChosenDish().getPrice()
            );
            //sum += orderList.getEntry(position).getQty() * orderList.getEntry(position).getChosenDish().getPrice();
        }
        // Report Id name date
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

    public static void main(String[] args) {
        SortedLinkedListInterface<Report> reportList = new SortedLinkedList<>();
        ListInterface<OrderDish> orderList = new LinkedList<>();
        QueueInterface<Order> orderedList = new LinkedQueue<>();

        ReportOperation reportOp = new ReportOperation();

        reportOp.display_Daily_Sales_Report();
    }

}
