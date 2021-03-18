package adt;

import entity.Report;

public class ReportTestDriver {
    public static void main(String[] args) {
        SortedLinkedListInterface<Report> reportList = new SortedLinkedList<>();

        System.out.println("Is report empty: " + reportList.isEmpty());

        /*reportList.add(new Report("RE001", "Red"));
        reportList.add(new Report("RE002", "Expendables"));
        reportList.add(new Report("RE003", "Inception"));
        reportList.add(new Report("RE004", "Avengers"));
        reportList.add(new Report("RE005", "Thor: Ragnarok"));
        reportList.add(new Report("RE006", "Avatar"));
        reportList.add(new Report("RE007", "Last Blood"));
        reportList.add(new Report("RE008", "last blood"));

        System.out.println("Report Name: \n" + reportList);

        System.out.println("Is report empty: " + reportList.isEmpty());*/

    }
}