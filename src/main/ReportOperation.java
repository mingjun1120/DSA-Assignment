package main;
import adt.*;
import adt.LinkedList;
import entity.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class ReportOperation {
    SortedLinkedListInterface<DailyReport> dailyReportList = new SortedLinkedList<>();
    ListInterface<OrderDish> orderList = new LinkedList<>();
    QueueInterface<Order> orderedList = new LinkedQueue<>();
    public static Scanner scan = new Scanner(System.in);

    DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss a");
    LocalDateTime now = LocalDateTime.now();

    public void display_Daily_Sales_Report() {
        readOrderedListTextFile();
        System.out.print("\n");
        System.out.println("\n+------------------------------------------------------------------------------------+");
        System.out.println("|                                  NOODLES UNLIMITED                                 |");
        System.out.println("|                        TARUC KUALA LUMPUR, JLN GENTING KLANG                       |");
        System.out.println("|                         Lot 5.103.00 - 5.105.00 & P5.10.00,                        |");
        System.out.println("|                             53300 Kuala Lumpur, Malaysia                           |");
        System.out.println("|                                                                                    |");
        System.out.println("|                                  Daily Sales Report                                |");
        System.out.println("|                                      -----------                                   |");
        System.out.printf("|%-11s  %-12s  %-10s  %-12s %-15s %-5s %s |\n", "Order ID", "Date", "Time", "Dish Name", "Price Each(RM)", "Qty", "Price(RM)");

        Iterator<Order> it = orderedList.getIterator();
        while(it.hasNext()) {
            Order order = it.next();
            dailyReportList.add(new DailyReport(order.getOrderTime(), order));
        }

        //Loop reportList & print it out
        Iterator<DailyReport> dr_it = dailyReportList.getIterator();
        while(dr_it.hasNext()) {
            DailyReport dr = dr_it.next();
            System.out.printf("|   %-4s | %-10s | %-11s |",
                    dr.getCustomerOrder().getOrderID(),
                    dr.getCusOrderDateTime().format(formatter_date),
                    dr.getCusOrderDateTime().format(formatter_time));

            Iterator<OrderDish> orderDishIt = dr.getCustomerOrder().getCusOrderWithQtySorted().getIterator();
            int count = 0;
            while(orderDishIt.hasNext()){
                OrderDish od = orderDishIt.next();
                if(count > 0)
                        System.out.printf("| %6s | %10s | %11s |", " ", " ", " ");
                System.out.printf(" %-13s |  %7.2f     | %2d  | %9.2f |\n",
                        od.getChosenDish().getName(),
                        od.getChosenDish().getPrice(),
                        od.getQty(),
                        od.getQty()*od.getChosenDish().getPrice());
                count++;
            }
//            System.out.println();
//            System.out.println(dr.getCustomerOrder().getOrderTotalPrice());
        }
        display_Report_Footer();
    }

    public void display_Weekly_Sales_Report() {
        readOrderedListTextFile();
        readOrderedListTextFile();
        System.out.print("\n");
        System.out.println("\n+------------------------------------------------------------------------------------+");
        System.out.println("|                                  NOODLES UNLIMITED                                 |");
        System.out.println("|                        TARUC KUALA LUMPUR, JLN GENTING KLANG                       |");
        System.out.println("|                         Lot 5.103.00 - 5.105.00 & P5.10.00,                        |");
        System.out.println("|                             53300 Kuala Lumpur, Malaysia                           |");
        System.out.println("|                                                                                    |");
        System.out.println("|                                 Weekly Sales Report                                |");
        System.out.println("|                                     -----------                                    |");
        System.out.printf("| %13s | %5s   | %5s   | %5s   | %5s   | %5s   | %5s   | %5s  | %5s  \n", " ", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Total");
        System.out.printf("| Chili Pan Mee\n");
        System.out.printf("| Wantan Mee\n");
        System.out.printf("| Maggie Goreng\n");
        System.out.printf("| Hokkien Mee\n");
        //System.out.printf("|%-11s  %-12s  %-10s  %-12s %-15s %-5s %s |\n", "Order ID", "Date", "Time", "Dish Name", "Price Each(RM)", "Qty", "Price(RM)");

    }

    public void display_Dish_Rankings_Report() {
        //read_data_from_File();
    }

    private void display_Report_Footer(){
        System.out.println("+------------------------------------------------------------------------------------+");
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
        reportOp.display_Weekly_Sales_Report();
        
    }

}
