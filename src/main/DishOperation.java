package main;

import adt.ArrayList;
import adt.ArrayListInterface;
import entity.Dish;
import java.io.*;
import java.util.Scanner;

import static main.mainClass.doSelection;

public class DishOperation {
    private static ArrayListInterface<Dish> menuList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    private Dish inputDishDetails(Dish d) {

        System.out.println("\n**NEW DISH REGISTRATION**");
        System.out.println("-------------------------");
        String name;
        String[] all_dish_name = getAllDishNames();
        do {
            System.out.print("Enter new dish name: ");
            name = getDishName(all_dish_name);
        } while (!(name.matches("^[A-Za-z ]+$") && !isExistingName(all_dish_name, name)));

        double price = -1;
        do {
            System.out.print("Enter new dish's price: RM ");
            price = getDishPrice(price);
        } while (price == -1);

        if (d == null) {
            return new Dish(name, price);
        } else {
            int id = Integer.parseInt(d.getId().substring(1));
            id++;
            return new Dish(name, price, id);
        }
    }

    private String getDishName(String[] all_dish_name) {
        String name;
        name = scan.nextLine();
        if(!name.matches("^[A-Za-z ]+$") || isExistingName(all_dish_name, name)){
            System.out.println("Invalid input! Please enter again!\n");
        }
        return name;
    }

    private double getDishPrice(double price) {
        if (scan.hasNextDouble()) {
            price = scan.nextDouble();
            if (price <= 0)
                price = -1;
        }
        scan.nextLine();
        if (price == -1) {
            System.out.println("Invalid input! Please enter again!\n");
        }
        return price;
    }
// ========================================================== Main Functions =============================================================
    public void addNewDish() {
        read_data_from_File();
        char anymore;
        do {
            Dish d = menuList.getEntry(menuList.getLength()); // Use to check ID. Return null if there is no any dishes
            Dish dish = inputDishDetails(d);
            menuList.add(dish);
            do {
                System.out.print("Anymore? (Y/N): ");
                anymore = isChar(scan);
                is_Yes_and_No(anymore);
            } while(anymore != 'Y' && anymore != 'N');
        } while (anymore != 'N');

        write_data_into_file();
    }

    public void editDishName(int dish_to_be_edited) {

        if (dish_to_be_edited == 0) {
            dish_to_be_edited = getDish_to_be_edited("Enter which dish you want to edit its name (1-");
        }

        if (dish_to_be_edited != menuList.getLength() + 1) {
            String name = menuList.getEntry(dish_to_be_edited).getName();

            String[] all_dish_name = getAllDishNames();

            int error;
            do{
                System.out.print("Enter the new name for " + name + ": ");
                String new_name = scan.nextLine();
                error = editDishNameConfirmation(dish_to_be_edited, all_dish_name, new_name);
            } while(error == -1);
        }
    }

    public void editDishPrice(int dish_to_be_edited) {

        if (dish_to_be_edited == 0) {
            dish_to_be_edited = getDish_to_be_edited("Enter which dish you want to edit its name (1-");
        }

        if (dish_to_be_edited != menuList.getLength() + 1) {
            double price = menuList.getEntry(dish_to_be_edited).getPrice();
            String name = menuList.getEntry(dish_to_be_edited).getName();
            int error;
            //double new_price;
            do{
                System.out.print("Enter the new price for " + name + ": ");
                error = editDishPriceConfirmation(dish_to_be_edited, price);
            } while(error == -1);
        }
    }

    public void removeDish() {
        int dish_to_be_deleted = getDish_to_be_deleted();

        while (dish_to_be_deleted != menuList.getLength() + 1){
            char ans;
            boolean yes_no_ans;
            do {
                ans = getAns("Are you sure you want to delete this dish? (Y/N): ");
                yes_no_ans = remove_confirmation(dish_to_be_deleted, ans);
            } while(yes_no_ans);

            if (ans == 'Y') {
                do {
                    ans = getAns("Anymore? (Y/N): ");
                    yes_no_ans = is_Yes_and_No(ans);
                    if (!yes_no_ans) { //yes_no_ans == false
                        if (ans == 'Y') {
                            dish_to_be_deleted = getDish_to_be_deleted();
                        } else {
                            dish_to_be_deleted = menuList.getLength() + 1;
                        }
                    }
                } while (yes_no_ans);
            } else{
                dish_to_be_deleted = menuList.getLength() + 1;
            }
        }
    }

    public void display() {
        read_data_from_File();
        System.out.print("\n");
        dish_Details_Header();
        for (int position = 1; position <= menuList.getLength(); position++) {
            System.out.printf("| %-4d    %-4s    %-21s    %-9.2f    |\n", position, menuList.getEntry(position).getId(),
                                menuList.getEntry(position).getName(), menuList.getEntry(position).getPrice());
        }
        System.out.println("+-------------------------------------------------------+");
    }

    public void search_by_id() {
        read_data_from_File();

        String id;
        int found = 0;
        System.out.print("Enter the dish ID to be searched: ");
        id = scan.nextLine();

        for (int position = 1; position <= menuList.getLength(); position++) {
            if (id.equalsIgnoreCase(menuList.getEntry(position).getId())) {
                found = 1;
            }
        }
        System.out.print("\n");
        if(found == 1) {
            int position_located = 0;
            dish_Details_Header();
            for (int position = 1; position <= menuList.getLength(); position++) {
                if (id.equalsIgnoreCase(menuList.getEntry(position).getId())) {
                    System.out.printf("| %-4d    %-4s    %-21s    %-9.2f    |\n", position, menuList.getEntry(position).getId(),
                                       menuList.getEntry(position).getName(), menuList.getEntry(position).getPrice());
                    position_located = position;
                }
            }
            System.out.println("+-------------------------------------------------------+");
            request_whether_to_edit(position_located);
        } else {
            System.out.println("There is no dish with " + id + " found!");
        }
    }

    private void request_whether_to_edit(int position_located) {
        char ans;
        do {
            System.out.print("Do you want to edit this dish? (Y/N): ");
            ans = isChar(scan);
            is_Yes_and_No(ans);
        } while (ans != 'Y' && ans != 'N');
        if (ans == 'Y') {
            int selection;
            do {
                edit_dish_Table();
                selection = doSelection(3, "Enter your choice (1-");
                switch (selection) {
                    case 1:
                        editDishName(position_located);
                        display();
                        break;
                    case 2:
                        editDishPrice(position_located);
                        display();
                        break;
                    default:
                }
            } while (selection != 3);
        }
    }

    public void search_by_name() {
        read_data_from_File();
        String name;
        int found = 0;
        do {
            System.out.print("\nEnter the dish name to be searched: ");
            name = scan.nextLine();

            if (!name.matches("^[A-Za-z ]+$")){
                System.out.println("\nInvalid input! Only can input English alphabets!");
            }
        } while (!name.matches("^[A-Za-z ]+$"));

        for (int position = 1; position <= menuList.getLength(); position++) {
            if (name.equalsIgnoreCase(menuList.getEntry(position).getName())) {
                found = 1;
                break;
            }
        }
        System.out.print("\n");
        if(found == 1) {
            int position_located = 0;
            dish_Details_Header();
            for (int position = 1; position <= menuList.getLength(); position++) {
                if (name.equalsIgnoreCase(menuList.getEntry(position).getName())){
                    System.out.printf("| %-4d    %-4s    %-21s    %-9.2f    |\n", position, menuList.getEntry(position).getId(),
                                       menuList.getEntry(position).getName(), menuList.getEntry(position).getPrice());
                    position_located = position;
                }
            }
            System.out.println("+-------------------------------------------------------+");
            request_whether_to_edit(position_located);
        } else {
            System.out.println("There is no dish called " + name + " found!");
        }
    }
//==============================================================================================================================================

    private int editDishNameConfirmation(int dish_to_be_edited, String[] all_dish_name, String new_name) {
        int error;
        if (isExistingName(all_dish_name, new_name) || !new_name.matches("^[A-Za-z ]+$")) {
            System.out.println("\nInvalid input! Please enter again!\n");
            error = -1;
        }
        else {
            menuList.getEntry(dish_to_be_edited).setName(new_name);
            write_data_into_file();
            error = 0;
        }
        return error;
    }

    private int editDishPriceConfirmation(int dish_to_be_edited, double price) {
        double new_price;
        int error = -1;
        if (scan.hasNextDouble()) {
            new_price = scan.nextDouble();
            if(price != new_price) {
                menuList.getEntry(dish_to_be_edited).setPrice(new_price);
                write_data_into_file();
                error = 1;
            }
        }
        scan.nextLine();
        if (error == -1) {
            System.out.println("\nInvalid input! Please enter again!\n");
        }
        return error;
    }

    private boolean remove_confirmation(int dish_to_be_deleted, char ans) {
        boolean yes_no_ans = is_Yes_and_No(ans);
        if (!yes_no_ans && ans == 'Y') { // !yes_no_ans -> yes_no_ans == false
            menuList.remove(dish_to_be_deleted);
            write_data_into_file();
            yes_no_ans = false;
        } else if (!yes_no_ans && ans == 'N') { // !yes_no_ans -> yes_no_ans == false
            yes_no_ans = false;
        } else
            yes_no_ans = true;
        return yes_no_ans;
    }

    private String[] getAllDishNames() {
        String[] all_dish_name = new String[menuList.getLength()];
        for (int i = 0; i < menuList.getLength(); i++) {
            all_dish_name[i] = menuList.getEntry(i + 1).getName();
        }
        return all_dish_name;
    }

    private boolean isExistingName(String[] names, String new_name) {
        boolean result = false;
        for (String name : names) {
            if (name.equalsIgnoreCase(new_name)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private int getDish_to_be_edited(String msg) {
        read_data_from_File();
        menuTable();
        return doSelection(menuList.getLength() + 1, msg);
    }

    private int getDish_to_be_deleted() {
        read_data_from_File();
        menuTable();
        System.out.println("PLEASE CHOOSE WISELY WHICH DISH WANTED TO BE DELETED!\n");
        return doSelection(menuList.getLength() + 1, "Enter which dish you want to delete (1-");
    }

    private boolean is_Yes_and_No(char ans) {
        if (ans != 'Y' && ans != 'N') {
            System.out.println("Invalid input! Please enter again!\n");
            return true;
        } else {
            return false;
        }
    }

    private void edit_dish_Table() {
        System.out.println("\n+-------------------------------------+");
        System.out.println("|                 EDIT                |");
        System.out.println("+-------------------------------------+");
        System.out.println("|            1. Dish name             |");
        System.out.println("|            2. Dish price            |");
        System.out.println("|            3. Exit                  |");
        System.out.println("+-------------------------------------+");
    }

    private void dish_Details_Header() {
        System.out.println("+-------------------------------------------------------+");
        System.out.println("|                     DISH DETAILS                      |");
        System.out.println("+-------------------------------------------------------+");
        System.out.printf("| %-4s    %-4s    %-21s    %-8s    |\n", "Dish", "ID", "Name", "Price(RM)");
        System.out.println("|-------------------------------------------------------|");
    }

    private void menuTable()
    {
        System.out.println("+----------------------------------------------------+");
        System.out.println("|                        MENU                        |");
        System.out.println("+----------------------------------------------------+");
        for (int i = 1; i <= menuList.getLength() + 1; i++)
        {
            if (i != menuList.getLength() + 1) {
                System.out.printf("|          %-2d. %-23s RM%-6.2f      |\n", i, menuList.getEntry(i).getName(), menuList.getEntry(i).getPrice());
            } else {
                System.out.printf("|          %-2d. %-38s|\n", i, "Exit");
            }
        }
        System.out.println("+----------------------------------------------------+");
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

    private char getAns(String msg) {
        char ans;
        System.out.print(msg);
        ans = isChar(scan);
        return ans;
    }

    private void write_data_into_file() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/menu.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(menuList);
            out.close();
            fileOut.close();
            //System.out.print("Serialized data is saved in src/menu.txt\n");
        } catch (IOException i) {
            i.printStackTrace();
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
}