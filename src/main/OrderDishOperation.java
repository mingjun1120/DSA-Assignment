package main;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static main.mainClass.doSelection;
import adt.*;
import entity.Dish;
import entity.Order;
import entity.OrderDish;

public class OrderDishOperation {
    DishListInterface<Dish> menuList = new DishArrayList<>();
    OrderListInterface<OrderDish> orderList = new OrderLinkedList<>();
    QueueInterface<Order> orderedList = new OrderQueueLinked<>();
    public static Scanner scan = new Scanner(System.in);

    public OrderDish inputDishDetails() {
        System.out.println("\n**ORDERING**");
        System.out.println("--------------");

        int choice = doSelection(getDishLen() + 1, "Enter your choice (1-");
        if (choice == getDishLen() + 1)
            return null;

        int qty;
        do {
            System.out.print("Enter quantity: ");
            qty = isDigit();
            if (qty == -1){
                System.out.println("Invalid input! Please enter again!\n");
            }
        } while (qty == -1);

        return new OrderDish(qty, menuList.getEntry(choice));
    }

    public void addNewOrder() {
        menuTable();
        char anymore;
        do {
            OrderDish cus_order = inputDishDetails();
            if (cus_order == null)
                break;
            orderList.offer(cus_order);
            do {
                System.out.print("Anymore? (Y/N): ");
                anymore = isChar(scan);
            } while(is_Yes_and_No(anymore));
        } while (anymore != 'N');
    }

    public double displayOrder() {
        double sum = 0;
        System.out.print("\n");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.println("|                                    ORDER LIST                                    |");
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.printf("| %-4s    %-4s    %-18s    %-8s    %-18s    %-9s|\n", "No.", "ID", "Name", "Quantity", "Price Per Unit(RM)", "Total(RM)");
        System.out.println("|----------------------------------------------------------------------------------|");
        for (int position = 1; position <= orderList.getLength(); position++) {
            System.out.printf("| %-4d    %-4s    %-18s    %-8d    %-18.2f    %-9.2f|\n",
                    position,
                    orderList.getEntry(position).getChosenDish().getId(),
                    orderList.getEntry(position).getChosenDish().getName(),
                    orderList.getEntry(position).getQty(),
                    orderList.getEntry(position).getChosenDish().getPrice(),
                    orderList.getEntry(position).getQty() * orderList.getEntry(position).getChosenDish().getPrice()
            );
            sum += orderList.getEntry(position).getQty() * orderList.getEntry(position).getChosenDish().getPrice();
        }
        System.out.println("+----------------------------------------------------------------------------------+");
        System.out.printf("|%74s%-8.2f|\n","TOTAL PRICE NEED TO PAY:   RM", sum);
        System.out.println("+----------------------------------------------------------------------------------+");

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

        while (order_selected != orderList.getLength() + 1){
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

    private int getAmtPaidByCust(double sum, int error) {
        if (scan.hasNextDouble()) {
            double amt_received = scan.nextDouble();
            error = -2;
            if (amt_received >= sum) {
                System.out.println("\nORDERED SUCCESSFUL!");

                ZoneId zoneId = ZoneId.of("Asia/Kuala_Lumpur");
                LocalDateTime localDateTime = LocalDateTime.now(zoneId);
                orderedList.enqueue(new Order(localDateTime, sum, orderList.getAllEntries()));

                print_receipt(sum, amt_received);

                //write_data_into_file();
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

        for (OrderDish ob : orderList.getAllEntries()) {
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

    public int getDishLen() {
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
            menuList = (DishArrayList<Dish>)in.readObject();
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
            FileOutputStream fileOut = new FileOutputStream("src/orderList.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orderList);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved in src/orderList.txt\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
