package adt;

import entity.Report;

public class ReportTestDriver {
    public static void main(String[] args) {
        ReportListInterface<Report> reportList = new SortedLinkedList<>();

        System.out.println("Report isEmpty: " + reportList.isEmpty());

        reportList.add(new Report("Red"));
        reportList.add(new Report("Expendables"));
        reportList.add(new Report("Inception"));
        reportList.add(new Report("Avengers"));
        reportList.add(new Report("Thor: Ragnarok"));
        reportList.add(new Report("Avatar"));
        reportList.add(new Report("Last Blood"));
        reportList.add(new Report("last blood"));

        System.out.println("Report isEmpty: " + reportList.isEmpty());

        System.out.println(reportList);

    }
}