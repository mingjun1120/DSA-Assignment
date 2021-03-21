package entity;

import java.time.LocalDateTime;

public class WeeklyReport implements Comparable<WeeklyReport>{
    private static final String reportName = "Weekly Report";
    private LocalDateTime cusOrderDateTime;
    private Order customerOrder;
    //private static int id_no = 1;

    public WeeklyReport(LocalDateTime cusOrderDateTime, Order customerOrder) {
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
    @Override
    public int compareTo(WeeklyReport o) {
        return (int) (this.customerOrder.getOrderTotalPrice() - o.customerOrder.getOrderTotalPrice());
    }
}
