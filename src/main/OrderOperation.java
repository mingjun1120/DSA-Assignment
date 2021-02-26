package main;
import java.io.*;
import java.util.*;
import static main.mainClass.doSelection;
import adt.DishList;
import adt.DishListInterface;
import adt.OrderQueueInterface;
import adt.OrderQueueLinked;
import entity.Dish;
import entity.OrderDish;
import org.w3c.dom.Node;

public class OrderOperation {
    DishListInterface<Dish> menuList = new DishList<>();
    OrderQueueInterface<OrderDish> orderList = new OrderQueueLinked<>();
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
            if (cus_order == null)
                break;
            orderList.enqueue(cus_order);
            do {
                System.out.print("Anymore? (Y/N): ");
                anymore = isChar(scan);
            } while(is_Yes_and_No(anymore));
        } while (anymore != 'N');
    }

    public void displayOrder() {
        //read_data_from_File();
        System.out.print("\n");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("|                         ORDER LIST                         |");
        System.out.println("+------------------------------------------------------------+");
        System.out.printf("| %-4s    %-4s    %-18s    %-8s    %-8s|\n", "No.", "ID", "Name", "Quantity", "Price(RM)");
        System.out.println("|------------------------------------------------------------|");
        for (int position = 1; position <= orderList.size(); position++) {
            System.out.printf("| %-4d    %-4s    %-18s    %-8d    %-9.2f|\n",
                    position,
                    orderList.getEntry(position).getChosenDish().getId(),
                    orderList.getEntry(position).getChosenDish().getName(),
                    orderList.getEntry(position).getQty(), orderList.getEntry(position).getQty()*menuList.getEntry(position).getPrice());
        }
        System.out.println("+------------------------------------------------------------+");

//        OrderDish[] orderDishList = orderList.getAllEntries();
//        for (OrderDish cus_order : orderDishList) {
//            System.out.println(cus_order);
//        }
    }

    public void menuTable()
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
            menuList = (DishList<Dish>)in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
    }
}
