package main;
import java.io.*;
import java.util.*;
import static main.mainClass.doSelection;

import adt.*;
import entity.Dish;
import entity.OrderDish;

public class OrderOperation {
    DishListInterface<Dish> menuList = new DishArrayList<>();
    OrderListInterface<OrderDish> orderList = new OrderLinkedList<>();
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
        } while (qty == -1);

        return new OrderDish(qty, menuList.getEntry(choice));
    }

    public void addNewOrder() {
        menuTable();
        char anymore;
        do {
            OrderDish cus_order = inputDishDetails();
            if (cus_order == null){
                orderList.clear();
                break;
            }
            orderList.offer(cus_order);
            do {
                System.out.print("Anymore? (Y/N): ");
                anymore = isChar(scan);
            } while(is_Yes_and_No(anymore));
        } while (anymore != 'N');

       // write_data_into_file();
    }

    public void displayOrder() {
        //read_data_from_File();
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

//        for (OrderDish cus_order : orderList.getAllEntries()) {
//            System.out.println(cus_order);
//        }
    }

    public void paymentConfirmation() {
        char anymore;
        do {
            System.out.println("Is there anything to add on or modify? (Y/N): ");
            anymore = isChar(scan);
        } while(is_Yes_and_No(anymore));

        if (anymore == 'Y') {

            System.out.println("+-------------------------------+");
            System.out.println("|           OPERATION           |");
            System.out.println("+-------------------------------+");
            System.out.println("|         1. Add Order          |");
            System.out.println("|         2. Modify             |");
            System.out.println("+-------------------------------+");
            int choice = doSelection(3, "Enter your choice (1-");
        } else {
            System.out.println("Confirm proceed to checkout? (Y/N): ");

        }
    }

    private void menuTable()
    {
        read_data_from_File();
        System.out.println("+----------------------------------------------------+");
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

//    private void write_data_into_file() {
//        try {
//            FileOutputStream fileOut = new FileOutputStream("src/orderList.txt");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(orderList);
//            out.close();
//            fileOut.close();
//            System.out.print("Serialized data is saved in src/orderList.txt\n");
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
//    }
}
