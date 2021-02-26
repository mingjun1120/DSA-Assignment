package main;

import java.util.*;

public class mainClass {
    public static Scanner scan = new Scanner(System.in);
    private static final DishOperation dishOp = new DishOperation();
    private static final OrderOperation orderOp = new OrderOperation();
    public static void main(String[] args) {

        int operation_selection;

        do {
            //Print operation (e.g. Order, Modify Menu, Report & etc.)
            operationMenuTable();
            operation_selection = doSelection(5, "Enter your choice (1-");
            int selection;
            switch (operation_selection)
            {
                case 1: orderOp.addNewOrder(); orderOp.displayOrder(); break;
                case 2: do {
                            modifyMenuTable();
                            selection = doSelection(5, "Enter your choice (1-");
                            switch_func(selection);
                        } while (selection != 5);
                        break;
                case 3: System.out.println("Hiii!"); break;
                case 4: System.out.println("Hiiii!"); break;
                default:
            }
        } while(operation_selection >= 1 && operation_selection <= 4);
    }

    private static void switch_func(int modify_selection) {
        switch (modify_selection) {
            case 1: addNewDish(); break;
            case 2: editDishName(); break;
            case 3: editDishPrice(); break;
            case 4: removeDish(); break;
            default:
        }
    }

    public static void operationMenuTable()
    {
        System.out.println("\n+------------------------------------+");
        System.out.println("|           Operation List           |");
        System.out.println("+------------------------------------+");
        System.out.println("|         1. Order                   |");
        System.out.println("|         2. Modify Menu             |");
        System.out.println("|         3. Transaction History     |");
        System.out.println("|         4. Report                  |");
        System.out.println("|         5. Exit                    |");
        System.out.println("+------------------------------------+");
    }

    public static void modifyMenuTable()
    {
        System.out.println("\n+------------------------------------+");
        System.out.println("|          Menu Operation            |");
        System.out.println("+------------------------------------+");
        System.out.println("|         1. Add new dish            |");
        System.out.println("|         2. Edit dish's name        |");
        System.out.println("|         3. Edit dish's price       |");
        System.out.println("|         4. Remove dish             |");
        System.out.println("|         5. Exit                    |");
        System.out.println("+------------------------------------+");
    }

    private static void addNewDish() {
        dishOp.addNewDish();
        dishOp.display();
    }

    private static void editDishName() {
        dishOp.editDishName();
        dishOp.display();
    }

    private static void editDishPrice() {
        dishOp.editDishPrice();
        dishOp.display();
    }

    private static void removeDish() {
        dishOp.removeDish();
        dishOp.display();
    }

    public static int doSelection(int menu_num, String message)
    {
        int selection = 0;
        do {
            System.out.printf( message + "%d): ", menu_num);
            selection = isDigit();
            if (selection != -1){
                if (selection > menu_num || selection < 1){
                    selection = -1;
                }
            }
            if (selection == -1)
            {
                System.out.println("Invalid input! Please enter again!\n");
            }
        } while (selection == -1);

        return selection;
    }

    public static int isDigit()
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
}
