package main;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static main.mainClass.doSelection;
import adt.*;
import adt.ArrayList;
import adt.LinkedList;
import entity.*;

public class OrderDishOperation {
    ArrayListInterface<Dish> menuList = new ArrayList<>();
    ListInterface<OrderDish> orderList = new LinkedList<>();
    QueueInterface<Order> orderedList = new LinkedQueue<>();
    QueueInterface<Order> current_ordered = new LinkedQueue<>();
    //StackInterface<TransHist> tran_history = new LinkedStack<>();
    public static Scanner scan = new Scanner(System.in);

    private OrderDish inputDishDetails() {
        System.out.println("\n**ORDERING**");
        System.out.println("--------------");

        int choice = doSelection(getDishLen() + 1, "Enter your choice (1-");
        if (choice == getDishLen() + 1)
            return null;

        int qty;
        do {
            System.out.print("Enter quantity: ");
            qty = isDigit();
            if (qty == -1) {
                System.out.println("Invalid input! Please enter again!\n");
            }
        } while (qty == -1);

        return new OrderDish(qty, menuList.getEntry(choice));
    }

    public void addNewOrder() {
        read_order_data_from_File();
        menuTable();
        char anymore;
        do {
            OrderDish cus_order = inputDishDetails();
            if (cus_order == null)
                break;
            orderList.add(cus_order);
            do {
                System.out.print("Anymore? (Y/N): ");
                anymore = isChar(scan);
            } while(is_Yes_and_No(anymore));
        } while (anymore != 'N');
    }

    public double displayOrder() {
        double sum = 0;
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------+");
        System.out.println("|                                     ORDER LIST                                      |");
        System.out.println("+-------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s    %-4s    %-21s    %-8s    %-18s    %-9s|\n", "No.", "ID", "Name", "Quantity", "Price Per Unit(RM)", "Total(RM)");
        System.out.println("|-------------------------------------------------------------------------------------|");
        for (int position = 1; position <= orderList.getLength(); position++) {
            System.out.printf("| %-4d    %-4s    %-24s    %-8d    %-18.2f    %-6.2f|\n",
                    position,
                    orderList.getEntry(position).getChosenDish().getId(),
                    orderList.getEntry(position).getChosenDish().getName(),
                    orderList.getEntry(position).getQty(),
                    orderList.getEntry(position).getChosenDish().getPrice(),
                    orderList.getEntry(position).getQty() * orderList.getEntry(position).getChosenDish().getPrice()
            );
            sum += orderList.getEntry(position).getQty() * orderList.getEntry(position).getChosenDish().getPrice();
        }
        System.out.println("+-------------------------------------------------------------------------------------+");
        System.out.printf("|%79s%-6.2f|\n","TOTAL PRICE NEED TO PAY:   RM", sum);
        System.out.println("+-------------------------------------------------------------------------------------+");

        //Object[] cus_order = orderList.getAllEntries();
        //for (Object ob : cus_order) {
        //    System.out.println(((OrderDish)ob).getChosenDish().getName());
        //}
        return sum;
    }

    public char addOn_or_modify_confirmation_before_payment() {
        char anymore;
        do {
            System.out.print("Is there anything to add on or modify? (Y/N): ");
            anymore = isChar(scan);
        } while(is_Yes_and_No(anymore));
        return anymore;
    }

    public void editOrderQty() {
        displayOrder();
        System.out.println("\nNOTE!!! ENTER " + (orderList.getLength() + 1) + " TO EXIT!");
        int order_selected = doSelection(orderList.getLength() + 1, "Enter your choice (1-");

        if (order_selected != orderList.getLength() + 1) {
            String name = orderList.getEntry(order_selected).getChosenDish().getName();
            int error;
            do{
                System.out.print("Enter the new quantity for " + name + ": ");
                error = editDishQtyConfirmation(order_selected);
            } while(error == -1);
        }
    }

    public void removeOrder() {
        displayOrder();
        System.out.println("\nNOTE!!! ENTER " + (orderList.getLength() + 1) + " TO EXIT!");
        int order_selected = doSelection(orderList.getLength() + 1, "Enter which order you want to remove (1-");

        while (order_selected != orderList.getLength() + 1) {
            char ans;
            boolean yes_no_ans;
            orderList.remove(order_selected);
            do {
                ans = getAns();
                yes_no_ans = is_Yes_and_No(ans);
                if (!yes_no_ans) { //yes_no_ans == false
                    if (ans == 'Y') {
                        displayOrder();
                        System.out.println("\nNOTE!!! ENTER " + (orderList.getLength() + 1) + " TO EXIT!");
                        order_selected = doSelection(orderList.getLength() + 1, "Enter which order you want to remove (1-");
                    } else {
                        order_selected = orderList.getLength() + 1;
                    }
                }
            } while (yes_no_ans);
        }
    }

    public void paymentConfirmation(double sum) {
        char proceed;
        do {
            System.out.print("Confirm proceed to checkout? (Y/N): ");
            proceed = isChar(scan);
        } while(is_Yes_and_No(proceed));

        if(proceed == 'Y') {
            int error = -1;
            do{
                System.out.print("Enter the amount to pay: ");
                error = getAmtPaidByCust(sum, error);
            } while(error == -1);
        } else {
            System.out.println("\nThis order has been successfully cancelled!");
            orderList.clear();
        }
    }

    public int operation(){
        System.out.println("\n+-------------------------------+");
        System.out.println("|           OPERATION           |");
        System.out.println("+-------------------------------+");
        System.out.println("|         1. Add Order          |");
        System.out.println("|         2. Edit quantity      |");
        System.out.println("|         3. Remove order       |");
        System.out.println("|         4. Exit               |");
        System.out.println("+-------------------------------+");
        return doSelection(4, "Enter your choice (1-");
    }

    public int viewOrderQueue() {
        int loopAgn = 0;
        int error;
        if (current_ordered.size() == 0) {
            System.out.println("\nTHERE IS NO ORDER IN QUEUE CURRENTLY!");
        } else {
            System.out.println("\n+--------------------------------+");
            System.out.println("|   ORDER ID WAITING TO BE OUT   |");
            System.out.println("+--------------------------------+");
            //System.out.println("|            1. OD100            |");
            Iterator<Order> it = current_ordered.getIterator();
            int position = 1;
            while (it.hasNext()){
                System.out.printf("|%12d. %-14s    |\n", position, it.next().getOrderID());
                position++;
            }
            System.out.println("+--------------------------------+");

            do {
                loopAgn = 0;
                error = 0;
                System.out.print("\nPress 'D' to dequeue an order (Q to exit): ");
                String ans = scan.nextLine();
                if (ans.length() == 1){
                    if (!(Character.toString(ans.charAt(0)).matches("^[dDqQ]?$"))) {
                        error = 1;
                    } else if (Character.toString(ans.charAt(0)).matches("^[dD]?$")){
                        current_ordered.dequeue();
                        loopAgn = 1;
                    } else {
                        return loopAgn;
                    }
                } else { error = 1; }
                if (ans.length() != 1 || error == 1) {
                    System.out.println("Invalid input! Please enter again!");
                }
            } while (error == 1);
        }
        return loopAgn;
    }

    private int getAmtPaidByCust(double sum, int error) {
        if (scan.hasNextDouble()) {
            double amt_received = scan.nextDouble();
            error = -2;
            if (amt_received >= sum) {
                System.out.println("\nORDERED SUCCESSFUL!");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
                ZoneId zoneId = ZoneId.of("Asia/Kuala_Lumpur");
                LocalDateTime localDateTime = LocalDateTime.now(zoneId);

                Order od = orderedList.getLast();
                enqueue_confirmed_order(sum, formatter, localDateTime, od);
                current_ordered.enqueue(new Order(orderedList.getLast().getOrderID(), orderedList.getLast().getOrderTime(), sum, orderList.getAllEntries()));

                read_tran_history_from_File();
                TransactionHistory th = TransHistOperation.tran_history.peek();
                push_confirmed_order_as_TranHis(localDateTime, th);

                orderList.clear();

                print_receipt(sum, amt_received);

                write_data_into_file(); // Write orderedList data
                write_tran_history_data_into_file(); // Write transaction history data
                error = 1;
            }
        }
        scan.nextLine();
        if (error == -1) {
            System.out.println("Invalid input! Please enter again!\n");
        }
        if(error == -2) {
            System.out.println("Insufficient collection! Please enter again!\n");
            error = -1;
        }
        return error;
    }

    private void push_confirmed_order_as_TranHis(LocalDateTime localDateTime, TransactionHistory th) {
        if (th == null){
            TransHistOperation.push(new TransactionHistory(localDateTime, orderedList.getLast()));
        } else {
            int id_no = Integer.parseInt(th.getTranID().substring(1));
            TransHistOperation.push(new TransactionHistory(++id_no, localDateTime, orderedList.getLast()));
        }
    }

    private void enqueue_confirmed_order(double sum, DateTimeFormatter formatter, LocalDateTime localDateTime, Order od) {
        if (od == null) {
            orderedList.enqueue(new Order(localDateTime, sum, orderList.getAllEntries()));
        } else {
            if (od.getOrderTime().format(formatter).equals(localDateTime.format(formatter))){ //compare date only
                int id_no = Integer.parseInt(od.getOrderID().substring(2));
                orderedList.enqueue(new Order(++id_no, localDateTime, sum, orderList.getAllEntries()));
            } else {
                orderedList.enqueue(new Order(localDateTime, sum, orderList.getAllEntries()));
            }
        }
    }

    private void print_receipt(double sum, double amt_received) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");

        System.out.println("\n+-----------------------------------------------------------------------------------------+");
        System.out.println("|                                    NOODLES UNLIMITED                                    |");
        System.out.println("|                          TARUC KUALA LUMPUR, JLN GENTING KLANG                          |");
        System.out.println("|                           Lot 5.103.00 - 5.105.00 & P5.10.00,                           |");
        System.out.println("|                               53300 Kuala Lumpur, Malaysia                              |");
        System.out.println("|                                                                                         |");
        System.out.println("|                                         RECEIPT                                         |");
        System.out.println("|                                       -----------                                       |");
         System.out.printf("|    ORDER ID: %-5s                                     DATETIME: %-15s |\n", orderedList.getLast().getOrderID(), orderedList.getLast().getOrderTime().format(formatter));
        System.out.println("|                                                                                         |");
        System.out.println("|                                                                                         |");
        System.out.println("|    +-------------------------------------------------------------------------------+    |");
        System.out.println("|    |  Dish ID  |        Dish Name        |  Qty  |  Price(RM)  |  Total Price(RM)  |    |");
        System.out.println("|    |-----------|-------------------------|-------|-------------|-------------------|    |");

        for (OrderDish ob : orderedList.getLast().getCusOrder()) {
            System.out.printf("|    |  %-9s| %-24s| %-6d| %-12.2f| %-18.2f|    |\n",
                    ob.getChosenDish().getId(),
                    ob.getChosenDish().getName(),
                    ob.getQty(),
                    ob.getChosenDish().getPrice(),
                    ob.getQty() * ob.getChosenDish().getPrice()
            );
        }

        System.out.println("|    +-------------------------------------------------------------------------------+    |");
         System.out.printf("|    |%57s  | %-18.2f|    |\n","TOTAL PRICE NEED TO PAY:   RM", sum);
        System.out.println("|    +-------------------------------------------------------------------------------+    |");
        System.out.println("|                                                                                         |");
        System.out.println("|                                                                                         |");
         System.out.printf("|                  %44s %8.2f                  |\n","TOTAL  :RM", orderedList.getLast().getOrderTotalPrice());
         System.out.printf("|                  %44s %8.2f                  |\n","PAID   :RM", amt_received);
         System.out.printf("|                  %44s %8.2f                  |\n","CHANGE :RM", amt_received - sum);
        System.out.println("|                                                                                         |");
        System.out.println("|                                                                                         |");
        System.out.println("|                                   Payment Successful!                                   |");
        System.out.println("|                              Thank You, Have a nice day =)                              |");
        System.out.println("+-----------------------------------------------------------------------------------------+");

    }

    private int editDishQtyConfirmation(int order_selected) {
        int new_qty;
        int error = -1;
        if (scan.hasNextInt()) {
            new_qty = scan.nextInt();
            orderList.getEntry(order_selected).setQty(new_qty);
            error = 1;
        }
        scan.nextLine();
        if (error == -1) {
            System.out.println("\nInvalid input! Please enter again!\n");
        }
        return error;
    }

    private void menuTable()
    {
        read_data_from_File();
        System.out.println("\n+----------------------------------------------------+");
        System.out.println("|                        MENU                        |");
        System.out.println("+----------------------------------------------------+");
        for (int i = 1; i <= getDishLen() + 1; i++)
        {
            if (i != getDishLen() + 1) {
                System.out.printf("|          %-2d. %-23s RM%-6.2f      |\n", i, menuList.getEntry(i).getName(), menuList.getEntry(i).getPrice());
            } else {
                System.out.printf("|          %-2d. %-38s|\n", i, "Exit");
            }
        }
        System.out.println("+----------------------------------------------------+");
    }

    private int getDishLen() {
        return menuList.getLength();
    }

    private char getAns() {
        char ans;
        System.out.print("Anymore? (Y/N): ");
        ans = isChar(scan);
        return ans;
    }

    private static int isDigit()
    {
        int selection;
        if(scan.hasNextInt()) {
            selection = scan.nextInt();
        }
        else{
            selection = -1;
        }
        scan.nextLine();
        return selection;
    }

    private char isChar(Scanner scan)
    {
        String input;
        input = scan.nextLine();
        char my_char;

        if(input.length() == 1 && input.matches("^[a-zA-Z]+$"))
        {
            my_char = Character.toUpperCase(input.charAt(0));

        } else{
            my_char = '~'; //Means false
        }
        return my_char;
    }

    private boolean is_Yes_and_No(char ans) {
        if (ans != 'Y' && ans != 'N') {
            System.out.println("Invalid input! Please enter again!\n");
            return true;
        } else {
            return false;
        }
    }

    private void read_data_from_File() {
        try {
            FileInputStream fileIn = new FileInputStream("src/menu.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            menuList = (ArrayList<Dish>)in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
    }

    private void read_order_data_from_File() {
        try {
            FileInputStream fileIn = new FileInputStream("src/orderedList.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            orderedList = (LinkedQueue<Order>)in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
    }

    public static void read_tran_history_from_File() {
        try {
            FileInputStream fileIn = new FileInputStream("src/tranHistory.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            TransHistOperation.tran_history = (LinkedStack<TransactionHistory>)in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
    }

    private void write_data_into_file() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/orderedList.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orderedList);
            out.close();
            fileOut.close();
            //System.out.print("Serialized data is saved in src/orderedList.txt\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void write_tran_history_data_into_file() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/tranHistory.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(TransHistOperation.tran_history);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
