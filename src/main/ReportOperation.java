package main;
import adt.*;
import adt.LinkedList;
import entity.*;
import java.text.*;
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
        System.out.println("\n+----------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                             NOODLES UNLIMITED                                            |");
        System.out.println("|                                   TARUC KUALA LUMPUR, JLN GENTING KLANG                                  |");
        System.out.println("|                                    Lot 5.103.00 - 5.105.00 & P5.10.00,                                   |");
        System.out.println("|                                        53300 Kuala Lumpur, Malaysia                                      |");
        System.out.println("|                                                                                                          |");
        System.out.println("|                                             Daily Sales Report                                           |");
        System.out.println("|                                                 -----------                                              |");
        System.out.println("|  +----------------------------------------------------------------------------------------------------+  |");
        System.out.printf("|  | %s |    %-7s |     %-7s |       %-15s | %s |  %s  |  %s  |  |\n", "Order ID", "Date", "Time", "Dish Name", "Price Each(RM)", "Qty", "Price(RM)");
        System.out.println("|  +----------------------------------------------------------------------------------------------------+  |");

        Iterator<Order> it = orderedList.getIterator();
        while(it.hasNext()) {
            Order order = it.next();
            dailyReportList.add(new DailyReport(order.getOrderTime(), order));
        }

        double daily_sales = 0;
        int daily_sold_qty = 0, daily_made_order = 0;

        //Loop reportList & print it out
        Iterator<DailyReport> dr_it = dailyReportList.getIterator();
        while(dr_it.hasNext()) {
            DailyReport dr = dr_it.next();
            System.out.printf("|  |   %-6s | %-10s | %-11s |",
                    dr.getCustomerOrder().getOrderID(),
                    dr.getCusOrderDateTime().format(formatter_date),
                    dr.getCusOrderDateTime().format(formatter_time));

            Iterator<OrderDish> orderDishIt = dr.getCustomerOrder().getCusOrderWithQtySorted().getIterator();
            int count = 0;
            while(orderDishIt.hasNext()){
                OrderDish od = orderDishIt.next();
                if(count > 0)
                        System.out.printf("|  | %8s | %10s | %11s |", " ", " ", " ");
                System.out.printf(" %-21s |    %7.2f     |  %2d   | %10.2f  |  |\n",
                        od.getChosenDish().getName(),
                        od.getChosenDish().getPrice(),
                        od.getQty(),
                        od.getQty() * od.getChosenDish().getPrice());

                daily_sold_qty += od.getQty();
                count++;
            }
            daily_made_order++;
            daily_sales += dr.getCustomerOrder().getOrderTotalPrice();
            System.out.println("|  +----------------------------------------------------------------------------------------------------+  |");
            System.out.printf("|  | %64s    %-16s | %10.2f  |  |\n", " ", "Total Price (RM)", dr.getCustomerOrder().getOrderTotalPrice());
            System.out.println("|  +----------------------------------------------------------------------------------------------------+  |");
        }
        System.out.printf("|  %103s |\n",' ');
        System.out.printf("|  Total Daily Sales     : RM %-76.2f |\n", daily_sales);
        System.out.printf("|  Total Dishes Sold     : %-79d |\n", daily_sold_qty);
        System.out.printf("|  Total Orders Have Made: %-79d |\n", daily_made_order);
        System.out.printf("|  %103s |\n",' ');
        System.out.println("+----------------------------------------------------------------------------------------------------------+");
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
        System.out.printf("| %3s | %s | %s | %s | %s | %s | %s | %s | %s | %s \n",
                " ", "Chili Pan Mee", "Dry Wantan Noodle", "Minced Pork Mee", "Sichuan Hot Soup Mee",
                "Special Sarawak Mee", "Curry Chicken Pan Mee", "Penang Laksa", "Braised Pork Ramen", "Total");

//        System.out.printf("| Mon\n");
//        System.out.printf("| Tue\n");
//        System.out.printf("| Wed\n");
//        System.out.printf("| Thu\n");
//        System.out.printf("| Fri\n");
//        System.out.printf("| Sat\n");
//        System.out.printf("| Sun\n");

//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//
//        DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
//        System.out.println(df.format(calendar.getTime()));
//        for (int i = 0; i < 7; i++) {
//            calendar.add(Calendar.DATE, 1);
//            System.out.println(df.format(calendar.getTime()));
//        }
//        System.out.println();
    }

    public void display_Dish_Rankings_Report() {
        //read_data_from_File();
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
        //reportOp.display_Weekly_Sales_Report();
        
    }

}
