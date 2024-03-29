package main;

import java.util.*;

public class mainClass {
    public static Scanner scan = new Scanner(System.in);
    private static final DishOperation dishOp = new DishOperation();
    private static final OrderDishOperation orderOp = new OrderDishOperation();
    private static final TransHistOperation tranHis = new TransHistOperation();
    private static final ReportOperation reportOp = new ReportOperation();

    public static void main(String[] args) {
        int operation_selection;

        logo();

        do {
            //Print operation (e.g. Order, Modify Menu, Report & etc.)
            operationMenuTable();
            operation_selection = doSelection(5, "Enter your choice (1-");
            int selection;
            switch (operation_selection)
            {
                case 1: do {
                             orderTable();
                             selection = doSelection(3, "Enter your choice (1-");
                             switch_func1(selection);
                        } while (selection != 3);
                        break;

                case 2: do {
                            modifyMenuTable();
                            selection = doSelection(7, "Enter your choice (1-");
                            switch_func2(selection);
                        } while (selection != 7);
                        break;

                case 3: do {
                            tranHistoryTable();
                            selection = doSelection(3, "Enter your choice (1-");
                            switch_func3(selection);
                        } while (selection != 3);
                        break;

                case 4: do {
                            reportTypeTable();
                            selection = doSelection(
                                    3, "Enter your choice (1-");
                            switch_func4(selection);
                        } while (selection != 3);
                        break;

                default: if (orderOp.current_ordered.size() > 0) {
                             System.out.println("\nTHERE ARE ORDERS STILL WAITING TO BE SENT OUT!!!\nPLEASE CLEAR THOSE ORDERS!!!");
                             operation_selection = 1;
                         }
            }
        } while(operation_selection >= 1 && operation_selection <= 4);
    }

    private static void logo() {
        System.out.println();
        System.out.println(" _   _                 _ _             _   _       _ _           _ _           _ ");
        System.out.println("| \\ | |               | | |           | | | |     | (_)         (_) |         | |");
        System.out.println("|  \\| | ___   ___   __| | | ___  ___  | | | |_ __ | |_ _ __ ___  _| |_ ___  __| |");
        System.out.println("| . ` |/ _ \\ / _ \\ / _` | |/ _ \\/ __| | | | | '_ \\| | | '_ ` _ \\| | __/ _ \\/ _` |");
        System.out.println("| |\\  | (_) | (_) | (_| | |  __/\\__ \\ | |_| | | | | | | | | | | | | ||  __/ (_| |");
        System.out.println("\\_| \\_/\\___/ \\___/ \\__,_|_|\\___||___/  \\___/|_| |_|_|_|_| |_| |_|_|\\__\\___|\\__,_|");
        System.out.println();
        System.out.println("Business Hour   : 8:00 a.m.  - 6:00 p.m.");
        System.out.println("Stall Phone No. : 09-20632063");
    }

    private static void orderQueue() {
        int loopAgn;
        do {
            loopAgn = orderOp.viewOrderQueue();
        } while (loopAgn == 1);
    }

    private static void switch_func1(int selection) {
        switch (selection) {
            case 1: make_order(); break;
            case 2: orderQueue(); break;
            default:
        }
    }

    private static void switch_func2(int modify_selection) {
        switch (modify_selection) {
            case 1: addNewDish(); break;
            case 2: editDishName(); break;
            case 3: editDishPrice(); break;
            case 4: removeDish(); break;
            case 5: searchDish(); break;
            case 6: dishOp.display(); break;
            default:
        }
    }

    private static void switch_func3(int selection) {
        switch (selection) {
            case 1: tranHis.print_Tran_History(); break;
            case 2: tranHis.print_filtered_Tran_History(); break;
            default:
        }
    }

    private static void switch_func4(int selection) {
        switch (selection) {
            case 1: reportOp.display_Daily_Sales_Report(); break;
            case 2: reportOp.display_Daily_Dish_Rankings_Report(); break;
            default:
        }
    }

    private static void tranHistoryTable() {
        System.out.println("\n+--------------------------------------+");
        System.out.println("|           View Transaction           |");
        System.out.println("+--------------------------------------+");
        System.out.println("|  1. Display all transaction history  |");
        System.out.println("|  2. Display through filter date      |");
        System.out.println("|  3. Exit                             |");
        System.out.println("+--------------------------------------+");
    }

    private static void operationMenuTable()
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

    private static void modifyMenuTable()
    {
        System.out.println("\n+------------------------------------+");
        System.out.println("|          Menu Operation            |");
        System.out.println("+------------------------------------+");
        System.out.println("|         1. Add new dish            |");
        System.out.println("|         2. Edit dish's name        |");
        System.out.println("|         3. Edit dish's price       |");
        System.out.println("|         4. Remove dish             |");
        System.out.println("|         5. Search dish             |");
        System.out.println("|         6. Display dish            |");
        System.out.println("|         7. Exit                    |");
        System.out.println("+------------------------------------+");
    }

    private static void orderTable()
    {
        System.out.println("\n+------------------------------------+");
        System.out.println("|          Order Operation           |");
        System.out.println("+------------------------------------+");
        System.out.println("|         1. Make order              |");
        System.out.println("|         2. View order in queue     |");
        System.out.println("|         3. Exit                    |");
        System.out.println("+------------------------------------+");
    }

    private static void reportTypeTable(){
        System.out.println("\n+------------------------------------+");
        System.out.println("|            Report Type             |");
        System.out.println("+------------------------------------+");
        System.out.println("|         1. Daily Sales Report      |");
        System.out.println("|         2. Daily Dish Rankings     |");
        System.out.println("|         3. Exit                    |");
        System.out.println("+------------------------------------+");
    }

    private static void search_table() {
        System.out.println("\n+-------------------------------------+");
        System.out.println("|              SEARCH BY              |");
        System.out.println("+-------------------------------------+");
        System.out.println("|            1. By dish ID            |");
        System.out.println("|            2. By dish name          |");
        System.out.println("|            3. Exit                  |");
        System.out.println("+-------------------------------------+");
    }

    private static void make_order() {
        char ans;
        double sum;
        int choice;
        do {
            choice = 0;
            orderOp.addNewOrder();
            sum = orderOp.displayOrder();
            ans = orderOp.addOn_or_modify_confirmation_before_payment();
            while (ans == 'Y') {
                choice = orderOp.operation();
                switch (choice) {
                    case 1 -> ans = 'N';
                    case 2 -> {
                        orderOp.editOrderQty();
                        sum = orderOp.displayOrder();
                        ans = orderOp.addOn_or_modify_confirmation_before_payment();
                    }
                    case 3 -> {
                        orderOp.removeOrder();
                        sum = orderOp.displayOrder();
                        ans = orderOp.addOn_or_modify_confirmation_before_payment();
                    }
                    default ->{
                        sum = orderOp.displayOrder();
                        ans = orderOp.addOn_or_modify_confirmation_before_payment();
                    }
                }
            }
        } while(choice == 1);
        if (sum > 0)
            orderOp.paymentConfirmation(sum);
    }

    private static void addNewDish() {
        dishOp.addNewDish();
        dishOp.display();
    }

    private static void editDishName() {
        dishOp.editDishName(0);
        dishOp.display();
    }

    private static void editDishPrice() {
        dishOp.editDishPrice(0);
        dishOp.display();
    }

    private static void removeDish() {
        dishOp.removeDish();
        dishOp.display();
    }

    private static void searchDish() {
        int selection;
        do {
            search_table();
            selection = doSelection(3, "Enter your choice (1-");
            switch (selection) {
                case 1: dishOp.search_by_id(); break;
                case 2: dishOp.search_by_name(); break;
                default:
            }
        } while (selection != 3);
    }

    public static int doSelection(int menu_num, String message)
    {
        int selection;
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
