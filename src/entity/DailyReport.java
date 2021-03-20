package entity;

import java.time.LocalDateTime;

public class DailyReport implements Comparable<DailyReport>{

    private static final String reportName = "Daily Report";
    private LocalDateTime cusOrderDateTime;
    private Order customerOrder;
    //private static int id_no = 1;

    public DailyReport(LocalDateTime cusOrderDateTime, Order customerOrder) {
        //this.reportID = "RPD".concat(String.valueOf(id_no++));
        this.cusOrderDateTime = cusOrderDateTime;
        this.customerOrder = customerOrder;
    }

    public static String getReportName() {
        return reportName;
    }

    public LocalDateTime getCusOrderDateTime() {
        return cusOrderDateTime;
    }

    public void setCusOrderDateTime(LocalDateTime cusOrderDateTime) {
        this.cusOrderDateTime = cusOrderDateTime;
    }

    public Order getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Order customerOrder) {
        this.customerOrder = customerOrder;
    }

    /*public static int getId_no() {
        return id_no;
    }

    public static void setId_no(int id_no) {
        DailyReport.id_no = id_no;
    }*/

    @Override
    public int compareTo(DailyReport o) {

        if (customerOrder.getOrderTime().isEqual(o.customerOrder.getOrderTime())) {
            return 0;
        } else if (customerOrder.getOrderTime().isBefore(o.customerOrder.getOrderTime())) {
            return -1;
        } else { // Means customerOrder.getOrderTime().isBefore(o.customerOrder.getOrderTime())
            return 1;
        }
    }
}
