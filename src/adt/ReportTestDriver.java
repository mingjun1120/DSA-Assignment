package adt;

import entity.DemoReport;
import entity.Report;

public class ReportTestDriver {
    public static void main(String[] args) {
        SortedLinkedListInterface<DemoReport> reportList = new SortedLinkedList<>();

        System.out.println("Is report empty: " + reportList.isEmpty());

        reportList.add(new DemoReport("Red"));
        reportList.add(new DemoReport("Expendables"));
        reportList.add(new DemoReport("Inception"));
        reportList.add(new DemoReport("Avengers"));
        reportList.add(new DemoReport("Thor: Ragnarok"));
        reportList.add(new DemoReport("Avatar"));
        reportList.add(new DemoReport("Last Blood"));
        reportList.add(new DemoReport("last blood"));

        System.out.println("Report Name: \n" + reportList);

        System.out.println("Is report empty: " + reportList.isEmpty());

    }
}