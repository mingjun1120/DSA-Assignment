package main;
import adt.*;
import adt.ArrayList;
import adt.LinkedList;
import entity.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class ReportOperation {
    SortedLinkedListInterface<DailySalesReport> dailyReportList = new SortedLinkedList<>();
    StackInterface<DailyDishRankingsReport> dailyDishRankingStacked = new LinkedStack<>();
    SortedLinkedListInterface<DailyDishRankingsReport> dailyDishRanking = new SortedLinkedList<>();
    ListInterface<OrderDish> orderList = new LinkedList<>();
    QueueInterface<Order> orderedList = new LinkedQueue<>();
    ArrayListInterface<Dish> dishList = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss a");

    public void display_Daily_Sales_Report() {
        dailyReportList.clear();
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
            dailyReportList.add(new DailySalesReport(order.getOrderTime(), order));
        }

        double daily_sales = 0;
        int daily_sold_qty = 0, daily_made_order = 0;

        //Loop reportList & print it out
        Iterator<DailySalesReport> dr_it = dailyReportList.getIterator();
        while(dr_it.hasNext()) {
            DailySalesReport dr = dr_it.next();
            if(formatter_date.format(LocalDateTime.now()).equals(dr.getCusOrderDateTime().format(formatter_date))) {
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
        }
        System.out.printf("|  %103s |\n",' ');
        System.out.printf("|  Total Daily Sales     : RM %-76.2f |\n", daily_sales);
        System.out.printf("|  Total Dishes Sold     : %-79d |\n", daily_sold_qty);
        System.out.printf("|  Total Orders Have Made: %-79d |\n", daily_made_order);
        System.out.printf("|  %103s |\n",' ');
        System.out.println("+----------------------------------------------------------------------------------------------------------+");


    }

    public void display_Daily_Dish_Rankings() {
        dailyDishRanking.clear();
        dailyReportList.clear();
        dailyDishRankingStacked.clear();

        readOrderedListTextFile();
        System.out.print("\n");
        System.out.println("\n+------------------------------------------------------------------------------------+");
        System.out.println("|                                  NOODLES UNLIMITED                                 |");
        System.out.println("|                        TARUC KUALA LUMPUR, JLN GENTING KLANG                       |");
        System.out.println("|                         Lot 5.103.00 - 5.105.00 & P5.10.00,                        |");
        System.out.println("|                             53300 Kuala Lumpur, Malaysia                           |");
        System.out.println("|                                                                                    |");
        System.out.println("|                                 Daily Dish Rankings                                |");
        System.out.println("|                                     -----------                                    |");
        System.out.println("|                                                                                    |");
        System.out.printf("|  %6s +---------------------------------------------------------------+  %7s |\n", " ", " ");
        System.out.printf("|  %14s  |  %-21s  |  %s  |  %-14s  |  %7s |\n", "|  Rank", "Dish Name", "Qty", "Total Sales (RM)", " ");
        System.out.printf("|  %6s +---------------------------------------------------------------+  %7s |\n", " ", " ");
        int chiliQty = 0, wantanQty = 0, mincedQty = 0, sichuanQty = 0, sarawakQty = 0, curryQty = 0, laksaQty = 0, braisedQty = 0;
        double chiliPrice = 0, wantanPrice = 0, mincedPrice = 0, sichuanPrice = 0, sarawakPrice = 0, curryPrice = 0, laksaPrice = 0, braisedPrice = 0;

        Iterator<Order> it = orderedList.getIterator();
        while(it.hasNext()) {
            Order order = it.next();
            dailyReportList.add(new DailySalesReport(order.getOrderTime(), order));
        }

        Iterator<DailySalesReport> dr_it = dailyReportList.getIterator();
        while(dr_it.hasNext()) {
            DailySalesReport dr = dr_it.next();
            if (formatter_date.format(LocalDateTime.now()).equals(dr.getCusOrderDateTime().format(formatter_date))) {
                Iterator<OrderDish> orderDishIt = dr.getCustomerOrder().getCusOrderWithQtySorted().getIterator();

                while (orderDishIt.hasNext()) {
                    OrderDish orderDish = orderDishIt.next();
                    if ("Chili Pan Mee".equals(orderDish.getChosenDish().getName())){
                        chiliQty += orderDish.getQty();
                        chiliPrice += orderDish.getQty() * orderDish.getChosenDish().getPrice();
                    }
                    else if ("Dry Wantan Noodle".equals(orderDish.getChosenDish().getName())){
                        wantanQty += orderDish.getQty();
                        wantanPrice += orderDish.getQty() * orderDish.getChosenDish().getPrice();
                    }
                    else if ("Minced Pork Mee".equals(orderDish.getChosenDish().getName())){
                        mincedQty += orderDish.getQty();
                        mincedPrice += orderDish.getQty() * orderDish.getChosenDish().getPrice();
                    }
                    else if ("Sichuan Hot Soup Mee".equals(orderDish.getChosenDish().getName())){
                        sichuanQty += orderDish.getQty();
                        sichuanPrice += orderDish.getQty() * orderDish.getChosenDish().getPrice();
                    }
                    else if ("Special Sarawak Mee".equals(orderDish.getChosenDish().getName())){
                        sarawakQty += orderDish.getQty();
                        sarawakPrice += orderDish.getQty() * orderDish.getChosenDish().getPrice();
                    }
                    else if ("Curry Chicken Pan Mee".equals(orderDish.getChosenDish().getName())){
                        curryQty += orderDish.getQty();
                        curryPrice += orderDish.getQty() * orderDish.getChosenDish().getPrice();
                    }
                    else if ("Penang Laksa".equals(orderDish.getChosenDish().getName())){
                        laksaQty += orderDish.getQty();
                        laksaPrice += orderDish.getQty() * orderDish.getChosenDish().getPrice();
                    }
                    else {
                        braisedQty += orderDish.getQty();
                        braisedPrice += orderDish.getQty() * orderDish.getChosenDish().getPrice();
                    }
                }
            }
        }
        dailyDishRanking.add(new DailyDishRankingsReport("Chili Pan Mee", chiliQty, chiliPrice));
        dailyDishRanking.add(new DailyDishRankingsReport("Dry Wantan Noddles", wantanQty, wantanPrice));
        dailyDishRanking.add(new DailyDishRankingsReport("Minced Pork Mee", mincedQty, mincedPrice));
        dailyDishRanking.add(new DailyDishRankingsReport("Sichuan Hot Soup Mee", sichuanQty, sichuanPrice));
        dailyDishRanking.add(new DailyDishRankingsReport("Special Sarawak Mee", sarawakQty, sarawakPrice));
        dailyDishRanking.add(new DailyDishRankingsReport("Curry Chicken Pan Mee", curryQty, curryPrice));
        dailyDishRanking.add(new DailyDishRankingsReport("Penang Laksa", laksaQty, laksaPrice));
        dailyDishRanking.add(new DailyDishRankingsReport("Braised Pork Ramen", braisedQty, braisedPrice));

        Iterator<DailyDishRankingsReport> ddr_it = dailyDishRanking.getIterator();
        while(ddr_it.hasNext()) {
            DailyDishRankingsReport ddr = ddr_it.next();
            dailyDishRankingStacked.push(ddr);
        }

        int i = 1;
        Iterator<DailyDishRankingsReport> ddrs_it = dailyDishRankingStacked.getIterator();
        while(ddrs_it.hasNext()){
            DailyDishRankingsReport ddrs = ddrs_it.next();
            System.out.printf("| %7s |  %3d   |  %-21s  |  %2d   |  %11.2f       | %10s\n",
                    " ", i, ddrs.getDishName(), ddrs.getOrderQty(), ddrs.getTotalPrice(), "|");
            i++;
        }
        System.out.printf("|  %6s +---------------------------------------------------------------+  %7s |\n", " ", " ");
        System.out.println("|                                                                                    |");
        System.out.println("|                                                                                    |");
        System.out.println("+------------------------------------------------------------------------------------+");
    }

    public void display_Weekly_Sales_Report() {
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
        ReportOperation reportOp = new ReportOperation();

        reportOp.display_Daily_Sales_Report();
        //reportOp.display_Weekly_Sales_Report();
        reportOp.display_Daily_Dish_Rankings();
        
    }

}
