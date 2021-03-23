package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import adt.*;
import entity.*;

public class TransHistOperation {
    public static Scanner scan = new Scanner(System.in);
    public static StackInterface<TransHist> tran_history = new LinkedStack<>();
    DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss a");

    public void print_Tran_History() {
        OrderDishOperation.read_tran_history_from_File();

        System.out.println("\n                   ^^ALL TRANSACTION HISTORY^^                  ");
        System.out.println("+----------------------------------------------------------------+");
        System.out.println("|                      TRANSACTION HISTORY                       |");
        System.out.println("+----------------------------------------------------------------+");
         System.out.printf("| %-7s  |  %-10s  |  %-11s  | %-8s | %-9s |\n", "TRAN ID", "DATE", "TIME", "ORDER ID", "TOTAL(RM)");
        System.out.println("+----------|--------------|---------------|----------|-----------+");
        Iterator<TransHist> it = tran_history.getIterator();
        while(it.hasNext()) {
            TransHist th = it.next();
            System.out.printf("|    %-5s |  %-10s  |  %-11s  |   %-5s  |   %-5.2f   |\n", th.getTranID(),
                    th.getTranTime().format(formatter_date),
                    th.getTranTime().format(formatter_time),
                    th.getTranDetail().getOrderID(),
                    th.getTranDetail().getOrderTotalPrice()
            );
        }
        System.out.println("+----------------------------------------------------------------+");
    }

    public void print_filtered_Tran_History() {
        OrderDishOperation.read_tran_history_from_File();

        String start_date_str;
        String end_date_str;
        do {
            System.out.println("\n********* STARTING DATE *********");
            System.out.println("---------------------------------");

            int month = -1;
            int day = -1;
            int year = -1;
            String startDay_input;
            String startMonth_input;

            //Input start month
            do{
                System.out.print("Enter the month: ");
                month = input_month(month);
            } while(month == -1);

            //Input start date
            do{
                System.out.print("Enter the day: ");
                day = input_day(day, month);
            } while(day == -1);

            //Input start year
            do{
                System.out.print("Enter the year (>= 2021): ");
                year = input_year(year);
            } while(year == -1);

            //Validate day
            if(String.valueOf(day).length() == 1){
                startDay_input = "0".concat(String.valueOf(day));
            } else {
                startDay_input = String.valueOf(day);
            }

            //Validate month
            if(String.valueOf(month).length() == 1){
                startMonth_input = "0".concat(String.valueOf(month));
            } else {
                startMonth_input = String.valueOf(month);
            }
            start_date_str = startDay_input + "/" + startMonth_input + "/" + year;

            System.out.println("\n********** ENDING DATE **********");
            System.out.println("---------------------------------");

            int end_month = -1;
            int end_day = -1;
            int end_year = -1;
            String endDay_input;
            String endMonth_input;

            //Input end month
            do{
                System.out.print("Enter the month: ");
                end_month = input_month(end_month);
            } while(end_month == -1);

            //Input end date
            do{
                System.out.print("Enter the day: ");
                end_day = input_day(end_day, end_month);
            } while(end_day == -1);

            //Input end year
            do{
                System.out.print("Enter the year (>= 2021): ");
                end_year = input_year(end_year);
            } while(end_year == -1);

            //Validate day
            if(String.valueOf(end_day).length() == 1){
                endDay_input = "0".concat(String.valueOf(end_day));
            } else {
                endDay_input = String.valueOf(end_day);
            }

            //Validate month
            if(String.valueOf(end_month).length() == 1){
                endMonth_input = "0".concat(String.valueOf(end_month));
            } else {
                endMonth_input = String.valueOf(end_month);
            }
            end_date_str = endDay_input + "/" + endMonth_input + "/" + end_year;

            if(LocalDate.parse(end_date_str, formatter_date).isBefore(LocalDate.parse(start_date_str, formatter_date)))
            {
                System.out.println("\nEnd date cannot be earlier than start date!!!\nPlease input the date again!");
            }
        } while (LocalDate.parse(end_date_str, formatter_date).isBefore(LocalDate.parse(start_date_str, formatter_date)));

        int found = 0;
        //LocalDate earliestDate = null;
        Iterator<TransHist> it = tran_history.getIterator();
        while(it.hasNext()) {
            TransHist th = it.next();
            if (LocalDate.parse(end_date_str, formatter_date).isEqual(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date)))
            {
                found = 1;
                break;
            }
            //earliestDate = LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date);
        }

        int cont = 0;
        int cont2 = 0;
        Iterator<TransHist> it2 = tran_history.getIterator();
        display_tranHis_header(start_date_str, end_date_str);
        if (found == 1) {
            while(it2.hasNext()) {
                TransHist th = it2.next();
                // || LocalDate.parse(start_date_str, formatter_date).isBefore(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date))
                // && LocalDate.parse(end_date_str, formatter_date).isAfter(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date))
                if (LocalDate.parse(end_date_str, formatter_date).isEqual(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date)))
                {
                    System.out.printf("|    %-5s |  %-10s  |  %-11s  |   %-5s  |   %-5.2f   |\n", th.getTranID(),
                            th.getTranTime().format(formatter_date),
                            th.getTranTime().format(formatter_time),
                            th.getTranDetail().getOrderID(),
                            th.getTranDetail().getOrderTotalPrice()
                    );
                    cont = 1;
                }
                if (!(LocalDate.parse(end_date_str, formatter_date).isEqual(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date))) && cont == 1)
                {
                    if(!(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date).isEqual(LocalDate.parse(start_date_str, formatter_date))) && cont2 == 0)
                    {
                        System.out.printf("|    %-5s |  %-10s  |  %-11s  |   %-5s  |   %-5.2f   |\n", th.getTranID(),
                                th.getTranTime().format(formatter_date),
                                th.getTranTime().format(formatter_time),
                                th.getTranDetail().getOrderID(),
                                th.getTranDetail().getOrderTotalPrice()
                        );
                    } else {
                        if (LocalDate.parse(start_date_str, formatter_date).isEqual(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date)))
                        {
                            System.out.printf("|    %-5s |  %-10s  |  %-11s  |   %-5s  |   %-5.2f   |\n", th.getTranID(),
                                    th.getTranTime().format(formatter_date),
                                    th.getTranTime().format(formatter_time),
                                    th.getTranDetail().getOrderID(),
                                    th.getTranDetail().getOrderTotalPrice()
                            );
                            cont2 = 1;
                        }
                    }
                }
            }
        } else {
                int check = 0;
                while(it2.hasNext()) {
                    TransHist th = it2.next();
                    if (!(LocalDate.parse(start_date_str, formatter_date).isEqual(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date))) && check == 0)
                    {
                        System.out.printf("|    %-5s |  %-10s  |  %-11s  |   %-5s  |   %-5.2f   |\n", th.getTranID(),
                                th.getTranTime().format(formatter_date),
                                th.getTranTime().format(formatter_time),
                                th.getTranDetail().getOrderID(),
                                th.getTranDetail().getOrderTotalPrice()
                        );
                    } else {
                        if (LocalDate.parse(start_date_str, formatter_date).isEqual(LocalDate.parse(th.getTranTime().format(formatter_date), formatter_date)))
                        {
                            System.out.printf("|    %-5s |  %-10s  |  %-11s  |   %-5s  |   %-5.2f   |\n", th.getTranID(),
                                    th.getTranTime().format(formatter_date),
                                    th.getTranTime().format(formatter_time),
                                    th.getTranDetail().getOrderID(),
                                    th.getTranDetail().getOrderTotalPrice()
                            );
                            check = 1;
                        }
                    }
                }
        }
        System.out.println("+----------------------------------------------------------------+");
    }

    private void display_tranHis_header(String start_date_str, String end_date_str) {
        System.out.println("\n     ^^TRANSACTION HISTORY FROM " + start_date_str + " to " + end_date_str + "^^");
        System.out.println("+----------------------------------------------------------------+");
        System.out.println("|                      TRANSACTION HISTORY                       |");
        System.out.println("+----------------------------------------------------------------+");
        System.out.printf("| %-7s  |  %-10s  |  %-11s  | %-8s | %-9s |\n", "TRAN ID", "DATE", "TIME", "ORDER ID", "TOTAL(RM)");
        System.out.println("+----------|--------------|---------------|----------|-----------+");
    }

    private void input_end_date(int end_month, int end_day, int end_year) {
        //Input end month
        do{
            System.out.print("Enter the month: ");
            end_month = input_month(end_month);
        } while(end_month == -1);

        //Input end date
        do{
            System.out.print("Enter the day: ");
            end_day = input_day(end_day, end_month);
        } while(end_day == -1);

        //Input end year
        do{
            System.out.print("Enter the year (>= 2021): ");
            end_year = input_year(end_year);
        } while(end_year == -1);
    }

    private void input_start_date(int month, int day, int year) {
        //Input start month
        do{
            System.out.print("Enter the month: ");
            month = input_month(month);
        } while(month == -1);

        //Input start date
        do{
            System.out.print("Enter the day: ");
            day = input_day(day, month);
        } while(day == -1);

        //Input start year
        do{
            System.out.print("Enter the year (>= 2021): ");
            year = input_year(year);
        } while(year == -1);
    }

    private int input_day(int error, int month) {
        int day;
        if (scan.hasNextInt()) {
            day = scan.nextInt();
            if (day >= 1 && day <= 31){
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                        error = day;
                }
                else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if(day > 30){
                        error = -2;
                    } else {
                        error = day;
                    }
                }
                else {
                    if(day > 28){
                        error = -3;
                    } else {
                        error = day;
                    }
                }

            } else {
                error = -1;
            }
        }
        scan.nextLine();

        if (error == -1) {
            System.out.println("Invalid input for day! Please enter again!\n");
        }
        if(error == -2) {
            System.out.printf("Month %d must <= 30 days! Please enter again!\n", month);
            error = -1;
        }
        if(error == -3) {
            System.out.printf("Month %d must <= 28 days! Please enter again!\n", month);
            error = -1;
        }

        return error;
    }

    private int input_month(int error) {
        int month;
        if (scan.hasNextInt()) {
            month = scan.nextInt();
            if (month >= 1 && month <= 12){
                error = month;
            } else {
                error = -1;
            }
        }
        scan.nextLine();

        if (error == -1) {
            System.out.println("Invalid input! Please enter again!\n");
        }

        return error;
    }

    private int input_year(int error) {
        int year;
        if (scan.hasNextInt()) {
            year = scan.nextInt();
            if (year >= 2021 && year <= 9999){
                error = year;
            } else {
                error = -1;
            }
        }
        scan.nextLine();
        if (error == -1) {
            System.out.println("Invalid input! Please enter again!\n");
        }
        return error;
    }

    public static void push(TransHist t){
        tran_history.push(t);
    }
}
