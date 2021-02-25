package main;
import java.io.*;
import java.util.*;
import static main.mainClass.doSelection;
import adt.DishList;
import adt.DishListInterface;
import entity.Dish;

public class OrderOperation {
    private static DishListInterface<Dish> menuList = new DishList<>();
    private static DishListInterface<Dish> orderList = new DishList<>();
    Scanner scan = new Scanner(System.in);

    public void addNewOrder() {

        String[] all_dish_name = getAllDishNames();
        char anymore;
        do {
            Dish dish = inputDishDetails(all_dish_name, d);
            menuList.add(dish);
            do {
                System.out.print("Anymore? (Y/N): ");
                anymore = isChar(scan);
                is_Yes_and_No(anymore);
            } while(anymore != 'Y' && anymore != 'N');
        } while (anymore != 'N');

        write_data_into_file();
    }

    public int orderDish() {

        return doSelection(menuList.getLength() + 1, "Enter your choice (1-");
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
