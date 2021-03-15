package main;

import java.io.*;
import java.util.*;

import adt.*;
import entity.*;

public class TransHistOperation {
    public static Scanner scan = new Scanner(System.in);
    StackInterface<TransHist> transaction = new StackLinkedList<TransHist>();
    private LinkedQueue<Order> orderedList;

    private void addTransaction(){
        read_order_data_from_File(orderedList);
        Order[] transactionsMade = orderedList.getAllEntries();
        transactionsMade.toString();

    }

    private void read_order_data_from_File(LinkedQueue<Order> orderedList) {
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

   /** private void write_data_into_file() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/transHist.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(TransactionHistory);
            out.close();
            fileOut.close();
            //System.out.print("Serialized data is saved in src/orderedList.txt\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }*/

}
